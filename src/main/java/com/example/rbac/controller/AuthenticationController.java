package com.example.rbac.controller;

import com.example.rbac.dto.ErrorResponse;
import com.example.rbac.dto.SignUpRequest;
import com.example.rbac.exception.ServiceException;
import com.example.rbac.service.AuthenticationService;
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
    public void signup(@RequestBody SignUpRequest signUpRequest) throws ServiceException {
            authenticationService.signup(signUpRequest);
    }

    @ExceptionHandler(value = ServiceException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorResponse handleInvalidInputException(ServiceException exception) {
        return new ErrorResponse(exception.getMessage());
    }
}
