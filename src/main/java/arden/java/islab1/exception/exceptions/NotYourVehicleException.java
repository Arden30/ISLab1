package arden.java.islab1.exception.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class NotYourVehicleException extends GeneralException {
    HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

    public NotYourVehicleException(String message) {
        super(message);
    }
}
