package porori.backend.job.domain.post.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EmploymentDuration {
    ONE_DAY("하루"),
    LESS_THAN_WEEK("1주일 이하"),
    ONE_WEEK_TO_ONE_MONTH("1주일~1개월"),
    ONE_MONTH_TO_THREE_MONTHS("1개월~3개월"),
    THREE_MONTHS_TO_SIX_MONTHS("3개월~6개월"),
    SIX_MONTHS_TO_ONE_YEAR("6개월~1년"),
    MORE_THAN_ONE_YEAR("1년이상");

    private final String message;
}
