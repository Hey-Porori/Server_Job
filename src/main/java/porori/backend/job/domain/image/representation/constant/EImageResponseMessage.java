package porori.backend.job.domain.image.representation.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EImageResponseMessage {
    ISSUE_PRESIGNED_URL_SUCCESS("presignedUrl을 발급하였습니다");

    private final String message;
}
