package arden.java.islab1.api.dto.request;

import jakarta.validation.constraints.NotNull;

public record AdminRightsRequest(
        @NotNull
        Long userId,
        @NotNull
        String username
) {
}
