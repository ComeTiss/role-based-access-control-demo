package com.example.rbac.dto;

import java.util.List;

public record RoleDto(String label, List<PrivilegeDto> privileges) {
}
