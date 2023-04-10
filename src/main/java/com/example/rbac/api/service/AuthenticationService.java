package com.example.rbac.api.service;

import com.example.rbac.api.dto.AuthenticationRequest;
import com.example.rbac.api.dto.LoginResponse;
import com.example.rbac.api.entity.Role;
import com.example.rbac.api.entity.RoleEnum;
import com.example.rbac.api.entity.User;
import com.example.rbac.api.repository.RoleRepository;
import com.example.rbac.api.repository.UserRepository;
import com.example.rbac.exception.ExistingUserException;
import com.example.rbac.exception.InvalidInputException;
import com.example.rbac.exception.InvalidLoginException;
import com.example.rbac.exception.ServiceException;
import com.example.rbac.security.Password;
import com.example.rbac.security.UserToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthenticationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    public void signup(AuthenticationRequest signUpRequest) throws ServiceException {
        if (!signUpRequest.isValid()) {
            throw new InvalidInputException("Invalid email or password provided");
        }

        if (userRepository.findExistByEmail(signUpRequest.email())) {
            throw new ExistingUserException("A user already exist for the email provided");
        }

        Role userRole = roleRepository.findByLabel(RoleEnum.USER.getLabel());
        User user = new User(signUpRequest.email(), signUpRequest.password(), List.of(userRole));
        userRepository.save(user);
    }

    public LoginResponse login(AuthenticationRequest loginRequest) throws ServiceException {
        if (!loginRequest.isValid()) {
            throw new InvalidLoginException("Invalid email or password provided");
        }

        Optional<User> userOpt = userRepository.findByEmail(loginRequest.email());
        if (userOpt.isEmpty()) {
            throw new InvalidLoginException("The email provided does not match any user.");
        }

        if (!Password.match(loginRequest.password(), userOpt.get().getPassword())) {
            throw new InvalidLoginException("Invalid password provided.");
        }

        return new LoginResponse(
                userOpt.get().getId(),
                userOpt.get().getEmail(),
                new UserToken(userOpt.get()).getValue()
        );
    }
}
