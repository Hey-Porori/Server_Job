package porori.backend.job.domain.post.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import porori.backend.job.domain.post.application.dto.request.GetAllPostRequest;
import porori.backend.job.domain.post.application.dto.response.GetAllPostResponse;
import porori.backend.job.domain.post.application.dto.response.GetPostResponse;
import porori.backend.job.domain.post.application.mapper.JobPostMapper;
import porori.backend.job.domain.post.domain.entity.JobPost;
import porori.backend.job.domain.post.domain.repository.JobRepository;
import porori.backend.job.domain.post.domain.repository.PostRepository;
import porori.backend.job.domain.post.domain.service.GetPostService;
import porori.backend.job.domain.user.application.dto.response.UserResponse;
import porori.backend.job.domain.user.application.service.GetUserResponseUserCase;

@Service
@RequiredArgsConstructor
public class GetPostUserCase {

    private final GetUserResponseUserCase getUserResponseUserCase;
    private final GetPostService getPostService;
    private final JobPostMapper jobPostMapper;
    private final PostRepository postRepository;
    public GetPostResponse getPost(String token, Long postId){
        UserResponse userResponse=getUserResponseUserCase.getUserData(token);
        JobPost jobPost=getPostService.findJobPostByJobPostId(postId);
        GetPostResponse getPostResponse=jobPostMapper.toDto(jobPost);
        getPostResponse.setCanUpdate(userResponse.getUserId() == jobPost.getWriterId());
        return getPostResponse;
    }

    public GetAllPostResponse getAllPost(String token, GetAllPostRequest getAllPostRequest){
        return postRepository.getAllPostResponse(getAllPostRequest);
//        return null;
    }
}
