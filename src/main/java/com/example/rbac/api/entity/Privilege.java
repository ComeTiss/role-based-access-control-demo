package com.example.rbac.api.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "privileges")
@NoArgsConstructor
public class Privilege {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String label;

    @ManyToMany(mappedBy = "privileges")
    private Set<Role> roles = new HashSet<>();

    public Privilege(String label) {
        this.label = label;
    }
}
