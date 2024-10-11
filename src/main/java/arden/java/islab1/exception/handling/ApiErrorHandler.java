package arden.java.islab1.exception.handling;

import arden.java.islab1.exception.exceptions.GeneralException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
public class ApiErrorHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        HttpStatus status = HttpStatus.valueOf(e.getStatusCode().value());
        String description = e.getBindingResult()
                .getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining("; "));

        ApiErrorResponse apiErrorResponse =
                buildDefaultErrorResponse(status, description, e);

        return ResponseEntity.status(status).body(apiErrorResponse);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiErrorResponse> handleConstraintViolationException(ConstraintViolationException e) {
        ApiErrorResponse apiErrorResponse =
                buildDefaultErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage(), e);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiErrorResponse);
    }

    @ExceptionHandler(GeneralException.class)
    public ResponseEntity<ApiErrorResponse> handleGeneralException(GeneralException exception) {
        ApiErrorResponse apiErrorResponse = buildDefaultErrorResponse(exception.getHttpStatus(),
                exception.getMessage(), exception
        );

        return ResponseEntity.status(exception.getHttpStatus()).body(apiErrorResponse);
    }

    private ApiErrorResponse buildDefaultErrorResponse(
            HttpStatus status,
            String description,
            Exception exception
    ) {
        String exceptionName = exception.getClass().getSimpleName();
        String exceptionMessage = exception.getMessage();

        return new ApiErrorResponse(
                description,
                status.toString(),
                exceptionName,
                exceptionMessage
        );
    }
}
