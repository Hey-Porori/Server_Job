package porori.backend.job.domain.image.application.dto.request;


import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import porori.backend.job.domain.image.application.dto.ImageFileExtension;
import porori.backend.job.global.annotation.ValidEnum;

@Builder
@Getter
@AllArgsConstructor
public class IssuePresignedUrlRequest {


    @NotBlank(message = "파일 확장자를 입력해주세요.")
    @ValidEnum(
            enumClass = ImageFileExtension.class,
            message = "유효하지 않은 ImageFileExtension 파라미터입니다.")
    private ImageFileExtension imageFileExtension;

}
