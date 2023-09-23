package porori.backend.job.domain.post.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import porori.backend.job.domain.post.domain.entity.Job;

public interface JobRepository extends JpaRepository<Job, Long> {
}
