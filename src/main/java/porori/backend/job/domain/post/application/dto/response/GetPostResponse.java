package porori.backend.job.domain.post.application.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import porori.backend.job.domain.post.constant.*;

import javax.persistence.*;
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
    private String writeDateBefore; //추가되는거
    private boolean canUpdate; //수정되는지 (추가되는거)

    // 근무 시간
    private LocalTime startTime;
    private LocalTime endTime;
    private boolean timeConsultable; // 시간 협의 가능 여부

    // 근무 요일
    private List<String> workDays = new ArrayList<>();

    // 근무 기간
    private String employmentDuration;
    private boolean durationConsultable; // 근무 기간 협의 가능 여부

    // 가게 위치 정보
    private String storeName;
    private double latitude;
    private double longitude;

    // 급여 정보
    private Integer salary;
    private String salaryType;
    private String currencyUnit;

    // 일종류
    private List<String> jobTypes = new ArrayList<>();
    public void setCanUpdate(boolean canUpdate){
        this.canUpdate=canUpdate;
    }
}
