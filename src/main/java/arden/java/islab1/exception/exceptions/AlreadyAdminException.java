package arden.java.islab1.exception.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class AlreadyAdminException extends GeneralException {
    private final HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

    public AlreadyAdminException(String message) {
        super(message);
    }
}
