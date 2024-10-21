package arden.java.islab1.api.dto.request;

import jakarta.validation.constraints.Min;

public record CoordinatesRequest(
        @Min(value = -118, message = "Minimum value of X is -118")
        double x,
        @Min(value = -652, message = "Minimum value of X is -652")
        double y
) {
}