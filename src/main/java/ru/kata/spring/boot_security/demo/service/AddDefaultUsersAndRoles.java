package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.UserDao;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.HashSet;
import java.util.Set;

@Service
public class AddDefaultUsersAndRoles {
    private UserDao userDao;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    public AddDefaultUsersAndRoles(UserDao userDao) {
        this.userDao = userDao;
        addDefaultRole();
        addDefaultUser();
    }
    @Transactional
    public void addDefaultUser() {
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(userDao.findById((long)1));
        Set<Role> roleSet2 = new HashSet<>();
        roleSet2.add(userDao.findById((long)1));
        roleSet2.add(userDao.findById((long)2));
        User user1 = new User("user", "user", "user", roleSet);
        User user2 = new User("admin", "admin", "admin", roleSet2);
        save(user1);
        save(user2);
    }

    @Transactional
    public void save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.save(user);
    }

    @Transactional
    public void addDefaultRole() {
        addRole(new Role("ROLE_USER"));
        addRole(new Role("ROLE_ADMIN"));
    }

    @Transactional
    public void addRole(Role role) {
        userDao.addRole(role);
    }

}
