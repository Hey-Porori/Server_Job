package porori.backend.job.global.exception;

public class InternalServerErrorException extends RuntimeException{

    public InternalServerErrorException(String message){ super(message); }
}