package porori.backend.job.domain.post.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import porori.backend.job.domain.post.domain.entity.Job;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
public class JobTypeDTO {
    private Long jobPostId;
    private List<Job> jobs = new ArrayList<>();
}
