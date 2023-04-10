package com.example.rbac.api.repository;

import com.example.rbac.api.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByLabel(String label);
}
