package arden.java.islab1.configuration;

import jakarta.validation.constraints.NotNull;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Validated
@ConfigurationProperties(prefix = "jwt.signing")
public record JwtConfig(
        @NotNull
        String accessKey
) {
}
