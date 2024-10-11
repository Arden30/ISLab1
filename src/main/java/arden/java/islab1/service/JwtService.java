package arden.java.islab1.service;

import arden.java.islab1.model.user.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {
    String extractUserName(String token);

    String generateToken(User user);

    boolean isTokenValid(String token, UserDetails userDetails);
}
