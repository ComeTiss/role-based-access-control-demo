package com.example.rbac.api.entity;

import com.example.rbac.security.Password;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "users")
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String email;

    private String password;

    public User(String email, String rawPassword) {
        this.email = email;
        this.password = new Password(rawPassword).getEncodedValue();
    }
}
