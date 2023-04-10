package com.example.rbac.api.mapper;

import com.example.rbac.api.controller.UserDto;
import com.example.rbac.api.entity.Privilege;
import com.example.rbac.api.entity.Role;
import com.example.rbac.api.entity.User;
import com.example.rbac.dto.PrivilegeDto;
import com.example.rbac.dto.RoleDto;

import java.util.Collection;
import java.util.List;

public class UserMapper {
    private UserMapper() {}

    public static UserDto toDto(User user) {
        return new UserDto(user.getId(), user.getEmail(), toRoleDtos(user.getRoles()));
    }

    private static List<RoleDto> toRoleDtos(Collection<Role> roles) {
        return roles.stream().map(UserMapper::toRoleDto).toList();
    }

    private static RoleDto toRoleDto(Role role) {
        return new RoleDto(role.getLabel(), toPrivilegeDtos(role.getPrivileges()));
    }
    private static List<PrivilegeDto> toPrivilegeDtos(Collection<Privilege> privileges) {
        return privileges.stream().map(UserMapper::toPrivilegeDto).toList();
    }

    private static PrivilegeDto toPrivilegeDto(Privilege privilege) {
        return new PrivilegeDto(privilege.getLabel());
    }
}
