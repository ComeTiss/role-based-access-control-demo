package com.example.rbac.service;

import com.example.rbac.dto.SignUpRequest;
import com.example.rbac.entity.User;
import com.example.rbac.exception.ExistingUserException;
import com.example.rbac.exception.InvalidInputException;
import com.example.rbac.exception.ServiceException;
import com.example.rbac.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    private UserRepository userRepository;

    public void signup(SignUpRequest signUpRequest) throws ServiceException {
        if (!signUpRequest.isValid()) {
            throw new InvalidInputException("Invalid email or password provided");
        }

        if (userRepository.findExistByEmail(signUpRequest.email())) {
            throw new ExistingUserException("A user already exist for the email provided");
        }

        userRepository.save(new User(signUpRequest.email(), signUpRequest.password()));
    }
}
