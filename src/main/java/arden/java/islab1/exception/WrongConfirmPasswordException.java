package arden.java.islab1.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class WrongConfirmPasswordException extends GeneralException {
    private final HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

    public WrongConfirmPasswordException(String message) {
        super(message);
    }
}
