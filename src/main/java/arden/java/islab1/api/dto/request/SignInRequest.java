package arden.java.islab1.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record SignInRequest(
        @Size(min = 5, max = 64, message = "Username can has from 5 to 64 chars")
        @NotBlank(message = "Username can't be blank")
        String username,

        @Size(min = 5, max = 128, message = "Password can has from 5 to 128 chars")
        @NotBlank(message = "Password can't be blank")
        String password
) {
}
