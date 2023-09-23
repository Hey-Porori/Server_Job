package porori.backend.job.domain.post.application.dto.request;

import lombok.Getter;
import lombok.Setter;
import porori.backend.job.domain.post.constant.DOW;
import porori.backend.job.domain.post.constant.EmploymentDuration;
import porori.backend.job.domain.post.constant.JobType;
import porori.backend.job.domain.post.constant.LocationDistance;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

@Getter
@Setter
public class GetAllPostRequest {

    @NotNull(message = "내 위치의 위도를 입력해 주세요.")
    private double latitude;

    @NotNull(message = "내 위치의 경도를 입력해 주세요.")
    private double longitude;
    @Enumerated(EnumType.STRING)
    private LocationDistance locationDistance;

    @Enumerated(EnumType.STRING)
    private List<JobType> jobTypes;

    @Enumerated(EnumType.STRING)
    private List<EmploymentDuration> employmentDurations;

    @NotNull(message = "근무기간 협의 가능 여부를 입력해 주세요.")
    private boolean durationConsultable; // 근무 기간 협의 가능 여부

    @Enumerated(EnumType.STRING)
    private List<DOW> dows;

    @NotBlank(message = "시작시간을 입력해주세요.")
    @Pattern(regexp = "([01]?[0-9]|2[0-3]):[0-5][0-9]"
            , message = "시간 형식이 맞지 않습니다. HH:mm 형식으로 입력해주세요.")
    private String startTime;

    @NotBlank(message = "종료시간을 입력해주세요.")
    @Pattern(regexp = "([01]?[0-9]|2[0-3]):[0-5][0-9]"
            , message = "시간 형식이 맞지 않습니다. HH:mm 형식으로 입력해주세요.")
    private String endTime;

    @NotNull(message = "시간 협의 가능 여부를 입력해 주세요.")
    private boolean timeConsultable; // 시간 협의 가능 여부
}
