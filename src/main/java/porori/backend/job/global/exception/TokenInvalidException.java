package porori.backend.job.global.exception;

import porori.backend.job.global.exception.constant.ExceptionList;

import static porori.backend.job.global.exception.constant.ExceptionList.TOKEN_INVALID_EXCEPTION;

public class TokenInvalidException extends ApplicationException{
    public TokenInvalidException(){
        super(TOKEN_INVALID_EXCEPTION.getErrorCode(),
                TOKEN_INVALID_EXCEPTION.getHttpStatus(),
                TOKEN_INVALID_EXCEPTION.getMessage());
    }
}
