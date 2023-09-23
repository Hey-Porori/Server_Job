package porori.backend.job.domain.post.application.dto.response;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import porori.backend.job.domain.post.constant.CurrencyUnit;
import porori.backend.job.domain.post.constant.EmploymentDuration;
import porori.backend.job.domain.post.constant.SalaryType;
import porori.backend.job.domain.post.domain.entity.Job;
import porori.backend.job.domain.post.domain.entity.WorkDay;

import java.time.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class PostBlocks {

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
    private List<String> jobTypes = new ArrayList<>();

    // 근무 시간
    private LocalTime startTime;
    private LocalTime endTime;
    private boolean timeConsultable; // 시간 협의 가능 여부

    // 근무 요일
    private List<String> workDays = new ArrayList<>();
    private String writeDateBefore;
    // 근무 기간
    private String employmentDuration;
    private boolean durationConsultable; // 근무 기간 협의 가능 여부

    @QueryProjection
    public PostBlocks(Long jobPostId, String title, String storeName, double latitude, double longitude,
                      Integer salary, SalaryType salaryType, CurrencyUnit currencyUnit,
                      LocalTime startTime, LocalTime endTime, boolean timeConsultable,
                      EmploymentDuration employmentDuration, boolean durationConsultable,
                      List<Job> jobs, List<WorkDay> workDays, LocalDateTime createdDate) {
        this.jobPostId=jobPostId;
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

        this.workDays = workDays.stream()
                .map(workDay -> workDay.getDow().getMessage())
                .collect(Collectors.toList());

        this.jobTypes = jobs.stream()
                .map(job -> job.getJobType().getMessage())
                .collect(Collectors.toList());

        ZonedDateTime seoulNow = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));
        LocalDateTime now = seoulNow.toLocalDateTime();
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
