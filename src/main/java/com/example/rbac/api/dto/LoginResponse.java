package com.example.rbac.api.dto;

public record LoginResponse(Long userId, String email, String token) {
}
