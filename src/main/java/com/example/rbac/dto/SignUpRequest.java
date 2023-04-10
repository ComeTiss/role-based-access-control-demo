package com.example.rbac.dto;

public record SignUpRequest(String email, String password) {

    public boolean isValid() {
        return isEmailValid() && isPasswordValid();
    }

    public boolean isEmailValid() {
        return notEmpty(email);
    }

    public boolean isPasswordValid() {
        return notEmpty(password);
    }

    private boolean notEmpty(String value) {
        return value != null && !value.isBlank();
    }
}
