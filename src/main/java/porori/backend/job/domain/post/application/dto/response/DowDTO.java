package porori.backend.job.domain.post.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import porori.backend.job.domain.post.domain.entity.WorkDay;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
public class DowDTO {
    private Long jobPostId;
    private List<WorkDay> workDays = new ArrayList<>();

}
