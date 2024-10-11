package arden.java.islab1.configuration;

import arden.java.islab1.model.user.Role;
import arden.java.islab1.repository.RoleRepository;
import arden.java.islab1.service.RoleService;
import arden.java.islab1.service.UserService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.MessageDigestPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {
    private final UserService userService;
    private final RoleService roleService;
    private final RoleRepository roleRepository;

    @PostConstruct
    public void init() {
        if (roleRepository.findByRole("USER").isEmpty()) {
            Role role = new Role();
            role.setRole("USER");
            roleRepository.save(role);
        }

        if (roleRepository.findByRole("ADMIN").isEmpty()) {
            Role role = new Role();
            role.setRole("ADMIN");
            roleRepository.save(role);
        }
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
        .cors(cors -> cors.configurationSource(request -> {
            var corsConfiguration = new CorsConfiguration();
            corsConfiguration.setAllowedOriginPatterns(List.of("*"));
            corsConfiguration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
            corsConfiguration.setAllowedHeaders(List.of("*"));
            corsConfiguration.setAllowCredentials(true);
            return corsConfiguration;
        }))
                .authorizeHttpRequests(request -> request
                        .requestMatchers("/api/v1/auth/**").permitAll()
                        .requestMatchers("/api/v1/vehicle/*").hasRole(roleService.getUserRole().getRole())
                        .requestMatchers("/swagger-ui/**", "/swagger-resources/*", "/v3/api-docs/**").permitAll()
//                        .requestMatchers("/endpoint", "/admin/**").hasRole("ADMIN_ROLE")
                        .anyRequest().authenticated())
                .sessionManagement(manager -> manager.sessionCreationPolicy(SessionCreationPolicy.ALWAYS))
//                .sessionManagement(manager -> manager.sessionCreationPolicy(STATELESS))
                .authenticationProvider(authenticationProvider());

        return http.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userService.userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config)
            throws Exception {

        return config.getAuthenticationManager();
    }
}
