package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;

import java.util.List;
import java.util.Set;
@Component
public interface RoleDao {

    Set<Role> findByIdRoles(List<Long> roles);

    List<Role> getRolesByUserId(Long id);

}
