package porori.backend.job.domain.post.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import porori.backend.job.domain.post.domain.entity.WorkDay;

public interface WorkDayRepository extends JpaRepository<WorkDay, Long> {
}
