package arden.java.islab1.service;

import arden.java.islab1.api.dto.request.SignInRequest;
import arden.java.islab1.api.dto.request.SignUpRequest;
import arden.java.islab1.api.dto.response.JwtResponse;
import arden.java.islab1.api.dto.response.SignUpResponse;

public interface AuthorizeService {
    SignUpResponse createUser(SignUpRequest signUpRequest);

    JwtResponse signIn(SignInRequest signInRequest);
}
