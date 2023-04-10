package com.example.rbac.api.repository;

import com.example.rbac.api.entity.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrivilegeRepository extends JpaRepository<Privilege, Long> {
    Privilege findByLabel(String label);
}
