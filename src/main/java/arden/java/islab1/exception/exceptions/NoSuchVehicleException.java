package arden.java.islab1.exception.exceptions;

import org.springframework.http.HttpStatus;

public class NoSuchVehicleException extends GeneralException {
    private final HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

    public NoSuchVehicleException(String message) {
        super(message);
    }
}
