package com.example.rbac.api.controller;

import com.example.rbac.api.dto.ErrorResponse;
import com.example.rbac.api.dto.LoginResponse;
import com.example.rbac.api.dto.AuthenticationRequest;
import com.example.rbac.exception.ServiceException;
import com.example.rbac.exception.InvalidLoginException;
import com.example.rbac.api.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/sign-up")
    public void signup(@RequestBody AuthenticationRequest signUpRequest) throws ServiceException {
        authenticationService.signup(signUpRequest);
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody AuthenticationRequest loginRequest) throws ServiceException {
        return authenticationService.login(loginRequest);
    }

    @ExceptionHandler(value = ServiceException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorResponse handleServiceException(ServiceException exception) {
        return new ErrorResponse(exception.getMessage());
    }

    @ExceptionHandler(value = InvalidLoginException.class)
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    public ErrorResponse handleInvalidLoginException(ServiceException exception) {
        return new ErrorResponse(exception.getMessage());
    }
}
