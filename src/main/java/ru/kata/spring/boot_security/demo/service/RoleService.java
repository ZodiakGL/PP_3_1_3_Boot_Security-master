package ru.kata.spring.boot_security.demo.service;

import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;

import java.util.List;
import java.util.Set;

@Component
public interface RoleService {

    Set<Role> findByIdRoles(List<Long> roles);

    List<Role> getRolesByUserId(Long id);

}
