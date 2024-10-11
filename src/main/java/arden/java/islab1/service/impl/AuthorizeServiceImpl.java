package arden.java.islab1.service.impl;

import arden.java.islab1.api.dto.request.SignInRequest;
import arden.java.islab1.api.dto.request.SignUpRequest;
import arden.java.islab1.api.dto.response.SignUpResponse;
import arden.java.islab1.exception.UserExistsException;
import arden.java.islab1.exception.WrongConfirmPasswordException;
import arden.java.islab1.model.user.User;
import arden.java.islab1.repository.UserRepository;
import arden.java.islab1.service.AuthorizeService;
import arden.java.islab1.service.RoleService;
import arden.java.islab1.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorizeServiceImpl implements AuthorizeService {
    private final RoleService roleService;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;

    @Override
    public SignUpResponse createUser(SignUpRequest signUpRequest) {
        if (!signUpRequest.password().equals(signUpRequest.confirmPassword())) {
            throw new WrongConfirmPasswordException("Passwords do not match");
        }

        User user = new User();
        user.setUsername(signUpRequest.username());
        user.getRoles().add(roleService.getUserRole());
        user.setPassword(passwordEncoder.encode(signUpRequest.password()));

        Optional<User> userByName = userRepository.findByUsername(user.getUsername());
        if (userByName.isPresent()) {
            throw new UserExistsException("User already exists, try another one");
        }

        userService.saveUser(user);

        return new SignUpResponse(user.getUsername(), user.getRoles());
    }

    @Override
    public User signIn(SignInRequest signInRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                signInRequest.username(),
                signInRequest.password()
        ));

        return userService.loadUserByUsername(signInRequest.username());
    }
}
