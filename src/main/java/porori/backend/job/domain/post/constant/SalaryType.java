package porori.backend.job.domain.post.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum SalaryType {
    ANNUAL("연봉"),
    MONTHLY("월급"),
    HOURLY("시급"),
    WEEKLY("주급");
    private String message;
}
