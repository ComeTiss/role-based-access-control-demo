package com.example.rbac.api.controller;

import com.example.rbac.api.dto.UserDto;
import com.example.rbac.api.service.UserService;
import com.example.rbac.security.annotation.IsAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/data/users")
public class UsersController {

    @Autowired
    private UserService userService;

    @IsAdmin
    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }
}
