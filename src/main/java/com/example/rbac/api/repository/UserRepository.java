package com.example.rbac.api.repository;

import com.example.rbac.api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT count(u) = 1 FROM User u where email = ?1")
    boolean findExistByEmail(String email);

    Optional<User> findByEmail(String email);
}
