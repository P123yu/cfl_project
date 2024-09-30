package com.cfl.cfl_project.service;

import com.cfl.cfl_project.model.Login;
import com.cfl.cfl_project.model.Register;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public interface AuthenticationAndAuthorizationService {

    // register   // send jwt token as response after register
    String register(Register register);

    // Login
    Authentication login(Login login);

    // find info By username
    Boolean isValidEmail(String username);

    // generate jwt By UserName
    String refreshToken(String userName);
}
