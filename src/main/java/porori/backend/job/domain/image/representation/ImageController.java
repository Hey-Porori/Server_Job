package porori.backend.job.domain.image.representation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import porori.backend.job.domain.image.application.dto.request.IssuePresignedUrlRequest;
import porori.backend.job.domain.image.application.dto.response.IssuePresignedUrlResponse;
import porori.backend.job.domain.image.application.service.IssuePresignedUrlUseCase;
import porori.backend.job.domain.image.representation.constant.EImageResponseMessage;
import porori.backend.job.global.ResponseDto;


@RestController
@RequestMapping("/image")
@RequiredArgsConstructor
public class ImageController {

    private final IssuePresignedUrlUseCase getPresignedUrlUseCase;


    @PostMapping("/presigned")
    public ResponseDto<IssuePresignedUrlResponse> createPresigned(
            @RequestBody IssuePresignedUrlRequest issuePresignedUrlRequest) {

        return ResponseDto.create(HttpStatus.OK.value(),
                EImageResponseMessage.ISSUE_PRESIGNED_URL_SUCCESS.getMessage(),
                getPresignedUrlUseCase.execute(issuePresignedUrlRequest));
    }
}
