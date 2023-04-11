package com.example.rbac.api.dto;

import java.util.List;

public record UserDto(Long id, String email, List<RoleDto> roles) {
}
