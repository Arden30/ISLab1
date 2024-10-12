package arden.java.islab1.api.dto.response;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record ChangeResponse(
        @NotNull
        String username,
        @NotNull
        LocalDateTime modifiedAt
) {
}
