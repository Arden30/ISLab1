package arden.java.islab1.api.dto.response;


import arden.java.islab1.model.user.Role;

import java.util.Set;

public record SignUpResponse(
        String username,
        Set<Role> roles
) {
}
