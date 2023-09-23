package porori.backend.job.domain.post.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import porori.backend.job.domain.post.domain.entity.JobPost;

public interface PostRepository extends JpaRepository<JobPost, Long> , PostCustomRepository {
    JobPost findByJobPostId(Long jobPostId);
}
