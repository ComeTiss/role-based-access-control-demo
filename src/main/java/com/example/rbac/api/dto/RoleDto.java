package com.example.rbac.api.dto;

import java.util.List;

public record RoleDto(String label, List<PrivilegeDto> privileges) {
}
