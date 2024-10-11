package arden.java.islab1.api.controller;

import arden.java.islab1.api.dto.request.SignInRequest;
import arden.java.islab1.api.dto.request.SignUpRequest;
import arden.java.islab1.api.dto.response.SignUpResponse;
import arden.java.islab1.model.user.User;
import arden.java.islab1.service.AuthorizeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthorizeController {
    private final AuthorizeService authorizeService;

    @PostMapping("/sign-up")
    public ResponseEntity<SignUpResponse> register(@RequestBody @Validated SignUpRequest signUpRequest) {
        return ResponseEntity.ok(authorizeService.createUser(signUpRequest));
    }

    @PostMapping("/sign-in")
    public ResponseEntity<User> register(@RequestBody @Validated SignInRequest signInRequest) {
        return ResponseEntity.ok(authorizeService.signIn(signInRequest));
    }
}

