package porori.backend.job.domain.post.application.dto.response;

import lombok.Builder;
import lombok.Getter;
import porori.backend.job.domain.post.constant.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
public class GetPostResponse {

    // 게시글 내용
    private Long jobPostId;
    private Long writerId;
    private String title;
    private String content;
    private String phoneNumber;
    private boolean isCompleted;
    private String imageUrl;
    private String writeDateBefore; //작성 일과 현재 비교
    private boolean canUpdate; //수정되는지 (추가되는거)

    // 근무 시간
    private LocalTime startTime;
    private LocalTime endTime;
    private boolean timeConsultable; // 시간 협의 가능 여부

    // 근무 요일
    @Enumerated(EnumType.STRING)
    private List<DOW> workDays = new ArrayList<>();

    // 근무 기간
    @Enumerated(EnumType.STRING)
    private EmploymentDuration employmentDuration;
    private boolean durationConsultable; // 근무 기간 협의 가능 여부

    // 가게 위치 정보
    private String storeName;
    private double latitude;
    private double longitude;

    // 급여 정보
    private Integer salary;

    @Enumerated(EnumType.STRING)
    private SalaryType salaryType;

    @Enumerated(EnumType.STRING)
    private CurrencyUnit currencyUnit;

    // 일종류
    @Enumerated(EnumType.STRING)
    private List<JobType> jobTypes = new ArrayList<>();
    public void setCanUpdate(boolean canUpdate){
        this.canUpdate=canUpdate;
    }
}
