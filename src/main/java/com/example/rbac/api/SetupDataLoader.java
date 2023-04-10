package com.example.rbac.api;

import com.example.rbac.api.entity.Privilege;
import com.example.rbac.api.entity.Role;
import com.example.rbac.api.entity.RoleEnum;
import com.example.rbac.api.entity.User;
import com.example.rbac.api.repository.PrivilegeRepository;
import com.example.rbac.api.repository.RoleRepository;
import com.example.rbac.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;

@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PrivilegeRepository privilegeRepository;

    @Autowired
    private UserRepository userRepository;

    private boolean alreadySetup = false;
    private final String ADMIN_EMAIL = "admin@gmail.com";

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (alreadySetup) {
            return;
        }

        Privilege readPrivilege = createPrivilegeIfNotFound("READ_PRIVILEGE");
        Privilege writePrivilege = createPrivilegeIfNotFound("WRITE_PRIVILEGE");

        Role adminRole = createRoleIfNotFound(RoleEnum.ADMIN.getLabel(), List.of(readPrivilege, writePrivilege));
        createRoleIfNotFound(RoleEnum.USER.getLabel(), List.of(readPrivilege));

        if (userRepository.findByEmail(ADMIN_EMAIL).isEmpty()) {
            User admin = new User(ADMIN_EMAIL, "password", List.of(adminRole));
            userRepository.save(admin);
        }

        alreadySetup = true;
    }

    @Transactional
    private Privilege createPrivilegeIfNotFound(String label) {
        Privilege privilege = privilegeRepository.findByLabel(label);
        if (privilege == null) {
            privilege = new Privilege(label);
            privilegeRepository.save(privilege);
        }
        return privilege;
    }

    @Transactional
    private Role createRoleIfNotFound(String label, Collection<Privilege> privileges) {
        Role role = roleRepository.findByLabel(label);
        if (role == null) {
            role = new Role(label);
            role.setPrivileges(privileges);
            roleRepository.save(role);
        }
        return role;
    }
}
