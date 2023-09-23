package porori.backend.job.domain.post.application.dto.request;

import lombok.Getter;
import porori.backend.job.domain.post.constant.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

@Getter
public class CreatePostRequest {
    @NotBlank(message = "제목을 입력해주세요.")
    private String title;

    @NotBlank(message = "상세내용을 입력해주세요.")
    private String content;

    @NotBlank(message = "핸드폰 번호를 입력해주세요.")
    private String phoneNumber;

    @NotBlank(message = "근무 시작시간을 입력해 주세요.")
    @Pattern(regexp = "([01]?[0-9]|2[0-3]):[0-5][0-9]"
            , message = "시간 형식이 맞지 않습니다. HH:mm 형식으로 입력해주세요.")
    private String startTime;

    @NotBlank(message = "근무 종료시간을 입력해 주세요.")
    @Pattern(regexp = "([01]?[0-9]|2[0-3]):[0-5][0-9]"
            , message = "시간 형식이 맞지 않습니다. HH:mm 형식으로 입력해주세요.")
    private String endTime;

    @NotNull(message = "시간 협의 가능 여부를 입력해 주세요.")
    private boolean timeConsultable;

    @Enumerated(EnumType.STRING)
    private List<DOW> workDays;

    @Enumerated(EnumType.STRING)
    private EmploymentDuration employmentDuration;

    @NotNull(message = "근무 기간 협의 가능 여부를 입력해 주세요.")
    private boolean durationConsultable;

    @NotBlank(message = "가게 이름을 입력해 주세요.")
    private String storeName;

    @NotNull(message = "가게 위치의 위도를 입력해 주세요.")
    private double latitude;

    @NotNull(message = "가게 위치의 경도를 입력해 주세요.")
    private double longitude;

    @NotNull(message = "급여를 입력해 주세요.")
    private Integer salary;

    @NotNull(message = "급여 유형을 입력해 주세요.")
    @Enumerated(EnumType.STRING)
    private SalaryType salaryType;

    @NotNull(message = "급여 금액 단위를 입력해 주세요.")
    @Enumerated(EnumType.STRING)
    private CurrencyUnit currencyUnit;

    @NotEmpty(message = "일 종류를 입력해 주세요.")
    @Enumerated(EnumType.STRING)
    private List<JobType> jobs = new ArrayList<>();

    @NotBlank(message = "이미지 url을 입력해 주세요.")
    private String imageUrl;
}
