package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.UserDao;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImp implements UserService, UserDetailsService {

    private final UserDao userDao;

    @Autowired
    public UserServiceImp(UserDao userDao) {
        this.userDao = userDao;
        addDefaultRole();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }

    @Override
    @Transactional
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    @Transactional
    public void addUser(User user) {
        userDao.addUser(user);

    }
    @Override
    @Transactional
    public User getUserById(int id) {
        return userDao.getUserById(id);
    }

    @Override
    @Transactional
    public void delete(int id) {
        userDao.delete(id);
    }

    @Override
    public User findByUserName(String name) {
        return userDao.getUserByName(name);
    }

    @Override
    public Collection<? extends GrantedAuthority> grantedAuthorities(Collection<Role> roles) {
        return roles.stream().map(el -> new SimpleGrantedAuthority(el.getName())).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void addRole(Role role) {
        userDao.addRole(role);
    }

    @Override
    @Transactional
    public void addDefaultRole() {
        addRole(new Role("ROLE_USER"));
        addRole(new Role("ROLE_ADMIN"));
    }

}
