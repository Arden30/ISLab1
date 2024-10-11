package arden.java.islab1.service.impl;

import arden.java.islab1.model.user.User;
import arden.java.islab1.repository.UserRepository;
import arden.java.islab1.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public User loadUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }

    @Override
    public User findUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("Can't find user with id: " + id));
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }
}
