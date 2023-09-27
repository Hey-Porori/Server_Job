package porori.backend.job.domain.post.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import porori.backend.job.domain.post.application.dto.request.CreatePostRequest;
import porori.backend.job.domain.post.application.mapper.JobPostMapper;
import porori.backend.job.domain.post.domain.entity.Job;
import porori.backend.job.domain.post.domain.entity.JobPost;
import porori.backend.job.domain.post.domain.entity.WorkDay;
import porori.backend.job.domain.post.domain.service.SavePostService;
import porori.backend.job.domain.user.application.dto.response.UserResponse;
import porori.backend.job.domain.user.application.service.GetUserResponseUserCase;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CreatePostUserCase {

    private final JobPostMapper jobPostMapper;

    private final SavePostService savePostService;

    private final GetUserResponseUserCase getUserResponseUserCase;

    public void createPost(String token, CreatePostRequest createPostRequest) {
        UserResponse userResponse = getUserResponseUserCase.getUserData(token);
        JobPost jobPost = jobPostMapper.toEntity(userResponse.getUserId(), createPostRequest);
        List<Job> jobs = jobPostMapper.createJobList(createPostRequest.getJobs(),jobPost);
        List<WorkDay> workDays = jobPostMapper.createWorkDayList(createPostRequest.getWorkDays(),jobPost);
        savePostService.savePost(jobPost,workDays,jobs);
    }

}
