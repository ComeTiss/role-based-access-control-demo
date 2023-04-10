package com.example.rbac.api.controller;

import com.example.rbac.dto.RoleDto;

import java.util.List;

public record UserDto(Long id, String email, List<RoleDto> roles) {
}
