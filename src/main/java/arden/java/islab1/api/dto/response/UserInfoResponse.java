package arden.java.islab1.api.dto.response;

import jakarta.validation.constraints.NotNull;

public record UserInfoResponse(
        @NotNull
        Long userId,
        @NotNull
        String username,
        @NotNull
        String role
) {
}
