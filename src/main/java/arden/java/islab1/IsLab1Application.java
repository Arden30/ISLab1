package arden.java.islab1;

import arden.java.islab1.configuration.JwtConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({JwtConfig.class})
public class IsLab1Application {

    public static void main(String[] args) {
        SpringApplication.run(IsLab1Application.class, args);
    }

}
