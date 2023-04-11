package com.example.rbac.api.mapper;

import com.example.rbac.api.dto.PrivilegeDto;
import com.example.rbac.api.dto.RoleDto;
import com.example.rbac.api.dto.UserDto;
import com.example.rbac.api.entity.Privilege;
import com.example.rbac.api.entity.Role;
import com.example.rbac.api.entity.User;

import java.util.Collection;
import java.util.List;

public class UserMapper {
    private UserMapper() {}

    public static UserDto toDto(User user) {
        return new UserDto(user.getId(), user.getEmail(), toRolesDto(user.getRoles()));
    }

    private static List<RoleDto> toRolesDto(Collection<Role> roles) {
        return roles.stream().map(UserMapper::toRoleDto).toList();
    }

    private static RoleDto toRoleDto(Role role) {
        return new RoleDto(role.getLabel(), toPrivilegesDto(role.getPrivileges()));
    }
    private static List<PrivilegeDto> toPrivilegesDto(Collection<Privilege> privileges) {
        return privileges.stream().map(UserMapper::toPrivilegeDto).toList();
    }

    private static PrivilegeDto toPrivilegeDto(Privilege privilege) {
        return new PrivilegeDto(privilege.getLabel());
    }
}
