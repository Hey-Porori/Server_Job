package porori.backend.job.global.exception.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ExceptionList {
    TOKEN_INVALID_EXCEPTION("U0001",HttpStatus.NOT_FOUND, "Access Token 이 유효하지 않습니다");
    private final String errorCode;
    private final HttpStatus httpStatus;
    private final String message;
}