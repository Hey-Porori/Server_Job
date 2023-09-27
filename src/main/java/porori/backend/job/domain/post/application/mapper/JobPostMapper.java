package porori.backend.job.domain.post.application.mapper;

import lombok.RequiredArgsConstructor;
import org.hibernate.jdbc.Work;
import org.springframework.stereotype.Component;
import porori.backend.job.domain.post.application.dto.request.CreatePostRequest;
import porori.backend.job.domain.post.application.dto.response.GetPostResponse;
import porori.backend.job.domain.post.application.dto.response.PostBlocks;
import porori.backend.job.domain.post.application.service.CalculateWriteDateBefore;
import porori.backend.job.domain.post.constant.DOW;
import porori.backend.job.domain.post.constant.JobType;
import porori.backend.job.domain.post.domain.entity.Job;
import porori.backend.job.domain.post.domain.entity.JobPost;
import porori.backend.job.domain.post.domain.entity.WorkDay;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class JobPostMapper {

    private final CalculateWriteDateBefore calculateWriteDateBefore;

    public JobPost toEntity(Long userId, CreatePostRequest createPostRequest) {
        return JobPost.builder()
                .writerId(userId)
                .title(createPostRequest.getTitle())
                .content(createPostRequest.getContent())
                .phoneNumber(createPostRequest.getPhoneNumber())
                .isCompleted(false)
                .startTime(LocalTime.parse(createPostRequest.getStartTime()))
                .endTime(LocalTime.parse(createPostRequest.getEndTime()))
                .timeConsultable(createPostRequest.isTimeConsultable())
                .employmentDuration(createPostRequest.getEmploymentDuration())
                .durationConsultable(createPostRequest.isDurationConsultable())
                .storeName(createPostRequest.getStoreName())
                .latitude(createPostRequest.getLatitude())
                .longitude(createPostRequest.getLongitude())
                .salary(createPostRequest.getSalary())
                .salaryType(createPostRequest.getSalaryType())
                .currencyUnit(createPostRequest.getCurrencyUnit())
                .imageUrl(createPostRequest.getImageUrl())
                .workDays(new ArrayList<>())
                .jobs(new ArrayList<>())
                .build();
    }

    public List<Job> createJobList(List<JobType> jobTypeList, JobPost jobPost) {
        return jobTypeList.stream()
                .map(jobDto -> {
                    Job job = new Job(jobDto);
                    job.setJobPost(jobPost); // Set relationship
                    return job;
                })
                .collect(Collectors.toList());
    }

    public List<WorkDay> createWorkDayList(List<DOW> dowList, JobPost jobPost) {
        return dowList.stream()
                .map(workDayDto -> {
                    WorkDay workDay = new WorkDay(workDayDto);
                    workDay.setJobPost(jobPost); // Set relationship
                    return workDay;
                })
                .collect(Collectors.toList());
    }

    public List<DOW> toDowList(List<WorkDay> workDayList){
        return workDayList.stream()
                .map(workDay -> workDay.getDow())
                .collect(Collectors.toList());
    }

    public List<JobType> toJobTypeList(List<Job> jobList){
        return jobList.stream()
                .map(job -> job.getJobType())
                .collect(Collectors.toList());
    }

    public GetPostResponse toDto(JobPost jobPost) {
        List<DOW> dow = toDowList(jobPost.getWorkDays());
        List<JobType> jobTypes = toJobTypeList(jobPost.getJobs());

        return GetPostResponse.builder()
                .jobPostId(jobPost.getJobPostId())
                .writerId(jobPost.getWriterId())
                .title(jobPost.getTitle())
                .content(jobPost.getContent())
                .phoneNumber(jobPost.getPhoneNumber())
                .isCompleted(jobPost.isCompleted())
                .imageUrl(jobPost.getImageUrl())
                .startTime(jobPost.getStartTime())
                .endTime(jobPost.getEndTime())
                .timeConsultable(jobPost.isTimeConsultable())
                .employmentDuration(jobPost.getEmploymentDuration())
                .storeName(jobPost.getStoreName())
                .latitude(jobPost.getLatitude())
                .longitude(jobPost.getLongitude())
                .salary(jobPost.getSalary())
                .salaryType(jobPost.getSalaryType())
                .currencyUnit(jobPost.getCurrencyUnit())
                .writeDateBefore(calculateWriteDateBefore.calculateWriteDateBefore(jobPost.getCreatedDate()))
                .workDays(dow)
                .jobTypes(jobTypes)
                .build();
    }

//    public PostBlocks toPostBlocks(JobPost jp) {
//        return new PostBlocks(
//                jp.getJobPostId(),
//                jp.getTitle(),
//                jp.getStoreName(),
//                jp.getLatitude(),
//                jp.getLongitude(),
//                jp.getSalary(),
//                jp.getSalaryType(),
//                jp.getCurrencyUnit(),
//                jp.getStartTime(),
//                jp.getEndTime(),
//                jp.isTimeConsultable(),
//                jp.getEmploymentDuration(),
//                jp.isDurationConsultable(),
//                jp.getJobs(),
//                jp.getWorkDays(),
//                jp.getCreatedDate()
//        );
//    }

}
