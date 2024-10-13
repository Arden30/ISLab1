package arden.java.islab1.api.dto.response;

import jakarta.validation.constraints.NotNull;

public record AdminRightsResponse(
        @NotNull
        Long userId,
        @NotNull
        Boolean isApproved
) {
}
