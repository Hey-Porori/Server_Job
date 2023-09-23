package porori.backend.job.domain.post.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import porori.backend.job.domain.post.domain.entity.JobPost;
import porori.backend.job.domain.post.domain.repository.PostRepository;

@Service
@RequiredArgsConstructor
public class GetPostService {
    private final PostRepository postRepository;
    public JobPost findJobPostByJobPostId(Long jobPostId){
        return postRepository.findByJobPostId(jobPostId);
    }
}
