package arden.java.islab1.api.dto.response;

import jakarta.validation.constraints.NotNull;

public record JwtResponse(
        @NotNull
        String accessToken
) {
}
