package arden.java.islab1.exception.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class UserExistsException extends GeneralException {
    private final HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

    public UserExistsException(final String message) {
        super(message);
    }
}
