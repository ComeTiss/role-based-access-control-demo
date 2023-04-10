package com.example.rbac.security;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class Password {

    @Getter
    private final String encodedValue;

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    public Password(String rawPassword) {
        this.encodedValue = passwordEncoder.encode(rawPassword);
    }

    public static boolean match(String rawPassword, String otherEncodedPassword) {
        return passwordEncoder.matches(rawPassword, otherEncodedPassword);
    }
}
