package porori.backend.job.domain.post.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import porori.backend.job.domain.post.application.dto.request.UpdatePostRequest;
import porori.backend.job.domain.post.application.mapper.JobPostMapper;
import porori.backend.job.domain.post.domain.entity.Job;
import porori.backend.job.domain.post.domain.entity.JobPost;
import porori.backend.job.domain.post.domain.entity.WorkDay;
import porori.backend.job.domain.post.domain.service.GetPostService;
import porori.backend.job.domain.post.domain.service.SavePostService;
import porori.backend.job.domain.user.application.dto.response.UserResponse;
import porori.backend.job.domain.user.application.service.GetUserResponseUserCase;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UpdatePostUserCase {
    private final GetUserResponseUserCase getUserResponseUserCase;
    private final GetPostService getPostService;
    private final SavePostService savePostService;
    private final JobPostMapper jobPostMapper;

    @Transactional
    public void updatePost(String token, Long postId, UpdatePostRequest updatePostRequest){
//        return null;
//        UserResponse userResponse=getUserResponseUserCase.getUserData(token);
//        JobPost jobPost=getPostService.findJobPostByJobPostId(postId);
//        List<Job> jobs = jobPostMapper.toJobList(updatePostRequest.getJobs(), jobPost);
//        List<WorkDay> workDays = jobPostMapper.toWorkDayList(updatePostRequest.getWorkDays(),jobPost);
    }
}
