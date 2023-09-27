package porori.backend.job.domain.post.application.dto.response;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import porori.backend.job.domain.post.constant.CurrencyUnit;
import porori.backend.job.domain.post.constant.EmploymentDuration;
import porori.backend.job.domain.post.constant.SalaryType;

import java.time.*;

@Getter
public class PostContent {
    private Long jobPostId;
    private String title;
    // 가게 위치 정보
    private String storeName;
    private double latitude;
    private double longitude;
    // 급여 정보
    private Integer salary;
    private String salaryType;
    private String currencyUnit;

    // 근무 시간
    private LocalTime startTime;
    private LocalTime endTime;
    private boolean timeConsultable; // 시간 협의 가능 여부

    // 근무 요일

    private String writeDateBefore;
    // 근무 기간
    private String employmentDuration;
    private boolean durationConsultable; // 근무 기간 협의 가능 여부

    @QueryProjection
    public PostContent(Long jobPostId, String title, String storeName, double latitude, double longitude,
                       Integer salary, SalaryType salaryType, CurrencyUnit currencyUnit,
                       LocalTime startTime, LocalTime endTime, boolean timeConsultable,
                       EmploymentDuration employmentDuration, boolean durationConsultable,
                       LocalDateTime createdDate) {
        this.jobPostId = jobPostId;
        this.title = title;
        this.storeName = storeName;
        this.latitude = latitude;
        this.longitude = longitude;
        this.salary = salary;
        this.salaryType = salaryType.getMessage();
        this.currencyUnit = currencyUnit.getMessage();
        this.startTime = startTime;
        this.endTime = endTime;
        this.timeConsultable = timeConsultable;
        this.employmentDuration = employmentDuration.getMessage();
        this.durationConsultable = durationConsultable;

        LocalDateTime now = ZonedDateTime.now(ZoneId.of("Asia/Seoul")).toLocalDateTime();
        Duration duration = Duration.between(createdDate, now);

        if (duration.toDays() >= 1) {
            this.writeDateBefore = duration.toDays() + "일 전";
        } else if (duration.toHours() >= 1) {
            this.writeDateBefore = duration.toHours() + "시간 전";
        } else {
            this.writeDateBefore = duration.toMinutes() + "분 전";
        }
    }
}
