package porori.backend.job.domain.post.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import porori.backend.job.domain.post.constant.*;
import porori.backend.job.global.entity.BaseTimeEntity;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class JobPost extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "job_post_id")
    private Long jobPostId;

    // 게시글 내용
    private Long writerId;
    private String title;
    private String content;
    private String phoneNumber;
    private boolean isCompleted; //모집 종료 여부
    private String imageUrl;

    // 근무 시간
    private LocalTime startTime;
    private LocalTime endTime;
    private boolean timeConsultable; // 시간 협의 가능 여부

    // 근무 요일
    @OneToMany(mappedBy = "jobPost", cascade = CascadeType.ALL)
    private List<WorkDay> workDays = new ArrayList<>();

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
    @OneToMany(mappedBy = "jobPost", cascade = CascadeType.ALL)
    private List<Job> jobs = new ArrayList<>();
}