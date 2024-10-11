package arden.java.islab1.service;

import arden.java.islab1.api.dto.request.SignInRequest;
import arden.java.islab1.api.dto.request.SignUpRequest;
import arden.java.islab1.api.dto.response.SignUpResponse;
import arden.java.islab1.model.user.User;

public interface AuthorizeService {
    SignUpResponse createUser(SignUpRequest signUpRequest);
    User signIn(SignInRequest signInRequest);
}
