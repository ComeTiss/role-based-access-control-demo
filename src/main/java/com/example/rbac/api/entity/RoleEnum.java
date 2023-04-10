package com.example.rbac.api.entity;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum RoleEnum {

    USER("USER"),
    ADMIN("ADMIN");
    private static final String ROLE_PREFIX  ="ROLE_";

    private final String label;

    public String getLabel() {
        return ROLE_PREFIX + label;
    }
}
