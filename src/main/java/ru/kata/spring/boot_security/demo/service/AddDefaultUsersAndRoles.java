package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
public class AddDefaultUsersAndRoles {
    private UserService userService;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    public AddDefaultUsersAndRoles(UserService userService) {
        this.userService = userService;
        addDefaultRole();
        addDefaultUser();
    }

    public void addDefaultUser() {
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(userService.findById((long)1));
        Set<Role> roleSet2 = new HashSet<>();
        roleSet2.add(userService.findById((long)1));
        roleSet2.add(userService.findById((long)2));
        User user1 = new User("user", "user", "user", roleSet);
        User user2 = new User("admin", "admin", "admin", roleSet2);
        save(user1);
        save(user2);
    }

    public void save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.save(user);
    }

    public void addDefaultRole() {
        addRole(new Role("ROLE_USER"));
        addRole(new Role("ROLE_ADMIN"));
    }

    public void addRole(Role role) {
        userService.addRole(role);
    }

}
