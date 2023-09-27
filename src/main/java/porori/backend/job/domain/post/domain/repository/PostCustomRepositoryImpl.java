package porori.backend.job.domain.post.domain.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import porori.backend.job.domain.post.application.dto.request.GetAllPostRequest;
import porori.backend.job.domain.post.application.dto.response.GetAllPostResponse;
import porori.backend.job.domain.post.application.dto.response.PostBlocks;
import porori.backend.job.domain.post.application.dto.response.PostContent;
import porori.backend.job.domain.post.application.dto.response.QPostContent;
import porori.backend.job.domain.post.constant.DOW;
import porori.backend.job.domain.post.constant.JobType;
import porori.backend.job.domain.post.domain.entity.Job;
import porori.backend.job.domain.post.domain.entity.WorkDay;
import porori.backend.job.domain.post.domain.repository.util.CalculateDistance.BoundingBox;

import javax.persistence.EntityManager;
import java.time.LocalTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static porori.backend.job.domain.post.domain.entity.QJob.job;
import static porori.backend.job.domain.post.domain.entity.QJobPost.jobPost;
import static porori.backend.job.domain.post.domain.entity.QWorkDay.workDay;
import static porori.backend.job.domain.post.domain.repository.util.CalculateDistance.calculateBoundingBox;
import static porori.backend.job.domain.post.domain.repository.util.CalculateDistance.haversineDistance;

public class PostCustomRepositoryImpl implements PostCustomRepository {

    private final JPAQueryFactory queryFactory;

    public PostCustomRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public GetAllPostResponse getAllPostResponse(GetAllPostRequest getAllPostRequest) {
        List<PostBlocks> postBlocks = getPostBlocks(getAllPostRequest);
        return new GetAllPostResponse(postBlocks.size(), postBlocks);
    }

    private List<PostBlocks> getPostBlocks(GetAllPostRequest getAllPostRequest) {

        BoundingBox box = calculateBoundingBox(getAllPostRequest.getLatitude(), getAllPostRequest.getLongitude(), getDistanceKm(getAllPostRequest));

        //게시글 내용만 전체 조회
        List<PostContent> postContents = queryFactory.select(new QPostContent(
                        jobPost.jobPostId,
                        jobPost.title,
                        jobPost.storeName,
                        jobPost.latitude,
                        jobPost.longitude,
                        jobPost.salary,
                        jobPost.salaryType,
                        jobPost.currencyUnit,
                        jobPost.startTime,
                        jobPost.endTime,
                        jobPost.timeConsultable,
                        jobPost.employmentDuration,
                        jobPost.durationConsultable,
                        jobPost.createdDate))
                .from(jobPost)
                .where(jobPost.latitude.between(box.minLatitude, box.maxLatitude)
                        .and(jobPost.longitude.between(box.minLongitude, box.maxLongitude)))
                .fetch();

        List<Long> jobPostIds = postContents.stream().map(PostContent::getJobPostId).collect(Collectors.toList());

        List<Job> jobs = queryFactory.selectFrom(job)
                .where(job.jobPost.jobPostId.in(jobPostIds))
                .fetch();

        List<WorkDay> workDays = queryFactory.selectFrom(workDay)
                .where(workDay.jobPost.jobPostId.in(jobPostIds))
                .fetch();

        Map<Long, List<JobType>> jobTypeMap = jobs.stream()
                .collect(Collectors.groupingBy(job -> job.getJobPost().getJobPostId(), // Job의 JobPost의 jobPostId를 사용하여 그룹화
                        Collectors.mapping(Job::getJobType, Collectors.toList())));

        Map<Long, List<DOW>> dowMap = workDays.stream()
                .collect(Collectors.groupingBy(workDay -> workDay.getJobPost().getJobPostId(), // WorkDay의 JobPost의 jobPostId를 사용하여 그룹화
                        Collectors.mapping(WorkDay::getDow, Collectors.toList())));

        List<PostBlocks> postBlocks = postContents.stream()
                .map(postContent -> {
                    List<JobType> jobTypesForCurrentPost = jobTypeMap.getOrDefault(postContent.getJobPostId(), Collections.emptyList());
                    List<DOW> dowsForCurrentPost = dowMap.getOrDefault(postContent.getJobPostId(), Collections.emptyList());
                    return new PostBlocks(postContent, jobTypesForCurrentPost, dowsForCurrentPost);
                })
                .collect(Collectors.toList());

        return postBlocks.stream()
                .filter(p -> isTimeMatching(p, getAllPostRequest))
                .filter(p -> areJobsAndWorkdaysMatching(p, getAllPostRequest))
                .filter(p -> isWithinDistance(p, getAllPostRequest))
                .filter(p -> areEmploymentDurationsMatching(p, getAllPostRequest))
                .collect(Collectors.toList());
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
    private boolean isTimeMatching(PostBlocks postBlocks, GetAllPostRequest request) {
        LocalTime requestStartTime = LocalTime.parse(request.getStartTime());
        LocalTime requestEndTime = LocalTime.parse(request.getEndTime());
        return request.isTimeConsultable() ||
                (postBlocks.getPostContent().getStartTime().equals(requestStartTime) || postBlocks.getPostContent().getStartTime().isAfter(requestStartTime)) &&
                        (postBlocks.getPostContent().getEndTime().equals(requestEndTime) || postBlocks.getPostContent().getEndTime().isBefore(requestEndTime));
    }

    // 게시물의 직업 유형 및 근무일이 요청과 일치하는지 확인
    private boolean areJobsAndWorkdaysMatching(PostBlocks postBlocks, GetAllPostRequest request) {

        return postBlocks.getJobTypes().containsAll(request.getJobTypes()) &&
                postBlocks.getDows().containsAll(request.getDows());
    }

    // 게시물의 고용 기간이 요청과 일치하는지 확인
    private boolean areEmploymentDurationsMatching(PostBlocks postBlocks, GetAllPostRequest request) {
        if (request.isDurationConsultable()) {
            return true;
        } else {
            return request.getEmploymentDurations().contains(postBlocks.getPostContent().getEmploymentDuration());
        }
    }

    // 게시물이 요청된 거리 내에 있는지 확인
    private boolean isWithinDistance(PostBlocks postBlocks, GetAllPostRequest request) {
        return haversineDistance(
                request.getLatitude(), request.getLongitude(),
                postBlocks.getPostContent().getLatitude(), postBlocks.getPostContent().getLongitude()
        ) <= getDistanceKm(request);
    }
}
