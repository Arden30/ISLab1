package arden.java.islab1.api.dto.response;

import arden.java.islab1.model.user.Role;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

public record SignUpResponse(
        @NotNull
        String username,
        @NotNull
        Set<Role> roles
) {
}
