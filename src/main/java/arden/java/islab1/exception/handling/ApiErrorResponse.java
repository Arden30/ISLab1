package arden.java.islab1.exception.handling;

public record ApiErrorResponse(
        String description,
        String status,
        String exceptionName,
        String exceptionMessage) {
}