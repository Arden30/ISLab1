package arden.java.islab1.exception.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class GeneralException extends RuntimeException {
    private final HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

    public GeneralException(final String message) {
        super(message);
    }
}
