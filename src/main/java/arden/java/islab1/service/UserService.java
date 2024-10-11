package arden.java.islab1.service;

import arden.java.islab1.model.user.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User loadUserByUsername(String username);

    User saveUser(User user);

    User getCurrentUser();

    User findUserById(Long id);
}
