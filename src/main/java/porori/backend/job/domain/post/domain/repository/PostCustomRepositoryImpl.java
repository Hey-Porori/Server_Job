package porori.backend.job.domain.post.domain.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.EntityGraph;
import porori.backend.job.domain.post.application.dto.request.GetAllPostRequest;
import porori.backend.job.domain.post.application.dto.response.GetAllPostResponse;
import porori.backend.job.domain.post.application.dto.response.PostBlocks;
import porori.backend.job.domain.post.application.mapper.JobPostMapper;
import porori.backend.job.domain.post.application.service.CalculateDistance.BoundingBox;
import porori.backend.job.domain.post.application.service.CalculateWriteDateBefore;
import porori.backend.job.domain.post.constant.DOW;
import porori.backend.job.domain.post.constant.EmploymentDuration;
import porori.backend.job.domain.post.constant.JobType;
import porori.backend.job.domain.post.domain.entity.Job;
import porori.backend.job.domain.post.domain.entity.JobPost;
import porori.backend.job.domain.post.domain.entity.WorkDay;

import javax.persistence.EntityManager;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

import static porori.backend.job.domain.post.application.service.CalculateDistance.calculateBoundingBox;
import static porori.backend.job.domain.post.application.service.CalculateDistance.haversineDistance;
import static porori.backend.job.domain.post.domain.entity.QJob.job;
import static porori.backend.job.domain.post.domain.entity.QJobPost.jobPost;
import static porori.backend.job.domain.post.domain.entity.QWorkDay.workDay;

public class PostCustomRepositoryImpl implements PostCustomRepository {

    // 필드 선언: QueryDSL을 위한 JPAQueryFactory, 날짜 계산 유틸리티 및 JobPost 매핑 유틸리티
    private final JPAQueryFactory queryFactory;
    private final CalculateWriteDateBefore calculateWriteDateBefore;
    private final JobPostMapper jobPostMapper;

    // 생성자: 필요한 의존성들을 주입
    public PostCustomRepositoryImpl(EntityManager em, JobPostMapper jobPostMapper) {
        this.queryFactory = new JPAQueryFactory(em);
        this.jobPostMapper = jobPostMapper;
        this.calculateWriteDateBefore = new CalculateWriteDateBefore();
    }

    @Override
    public GetAllPostResponse getAllPostResponse(GetAllPostRequest getAllPostRequest) {
        // 모든 게시글을 가져와서 DTO로 변환
        List<PostBlocks> postBlocks = getPostBlocks(getAllPostRequest)
                .stream()
                .map(jobPostMapper::toPostBlocks)
                .collect(Collectors.toList());
        return new GetAllPostResponse(postBlocks.size(), postBlocks);
    }

    private List<JobPost> getPostBlocks(GetAllPostRequest request) {
        // 위치를 기반으로 게시물을 가져온 후, 여러 조건에 따라 필터링
        List<JobPost> jobPosts = fetchJobPostsBasedOnLocation(request);

        return jobPosts.stream()
                .filter(jp -> isTimeMatching(jp, request))
                .filter(jp -> areJobsAndWorkdaysMatching(jp, request))
                .filter(jp -> isWithinDistance(jp, request))
                .filter(jp -> areEmploymentDurationsMatching(jp, request))
                .collect(Collectors.toList());
    }

    private List<JobPost> fetchJobPostsBasedOnLocation(GetAllPostRequest request) {
        // 지정된 위치와 거리를 기반으로 게시물을 가져오는 쿼리
        BoundingBox box = calculateBoundingBox(request.getLatitude(), request.getLongitude(), getDistanceKm(request));

        return queryFactory
                .selectFrom(jobPost)
                .where(jobPost.latitude.between(box.minLatitude, box.maxLatitude)
                        .and(jobPost.longitude.between(box.minLongitude, box.maxLongitude)))
                .fetch();
    }

    // 요청된 위치 거리 수준에 따른 거리를 반환
    private double getDistanceKm(GetAllPostRequest request) {
        switch (request.getLocationDistance()) {
            case LEVEL_1:
                return 2;
            case LEVEL_2:
                return 4;
            case LEVEL_3:
                return 6;
            default:
                throw new IllegalArgumentException("Unsupported distance level");
        }
    }

    // 게시물의 시간과 요청된 시간이 일치하는지 확인
    private boolean isTimeMatching(JobPost jp, GetAllPostRequest request) {
        LocalTime requestStartTime = LocalTime.parse(request.getStartTime());
        LocalTime requestEndTime = LocalTime.parse(request.getEndTime());
        return request.isTimeConsultable() ||
                (jp.getStartTime().equals(requestStartTime) || jp.getStartTime().isAfter(requestStartTime)) &&
                        (jp.getEndTime().equals(requestEndTime) || jp.getEndTime().isBefore(requestEndTime));
    }

    // 게시물의 직업 유형 및 근무일이 요청과 일치하는지 확인
    private boolean areJobsAndWorkdaysMatching(JobPost jp, GetAllPostRequest request) {
        List<JobType> jobTypesFromJobPost = jp.getJobs().stream()
                .map(Job::getJobType)
                .collect(Collectors.toList());

        List<DOW> dowsFromJobPost = jp.getWorkDays().stream()
                .map(WorkDay::getDow)
                .collect(Collectors.toList());

        return jobTypesFromJobPost.containsAll(request.getJobTypes()) &&
                dowsFromJobPost.containsAll(request.getDows());
    }

    // 게시물의 고용 기간이 요청과 일치하는지 확인
    private boolean areEmploymentDurationsMatching(JobPost jp, GetAllPostRequest request) {
        if (request.isDurationConsultable()) {
            return true;
        } else {
            return request.getEmploymentDurations().contains(jp.getEmploymentDuration());
        }
    }

    // 게시물이 요청된 거리 내에 있는지 확인
    private boolean isWithinDistance(JobPost jp, GetAllPostRequest request) {
        return haversineDistance(
                request.getLatitude(), request.getLongitude(),
                jp.getLatitude(), jp.getLongitude()
        ) <= getDistanceKm(request);
    }
}
