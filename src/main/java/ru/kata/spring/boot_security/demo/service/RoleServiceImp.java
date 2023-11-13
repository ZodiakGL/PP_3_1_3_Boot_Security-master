package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.RoleDao;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;
import java.util.Set;
@Service
@Transactional
public class RoleServiceImp implements RoleService {

    private RoleDao roleDao;
    @Autowired
    public RoleServiceImp(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    @Transactional
    public Set<Role> findByIdRoles(List<Long> roles) {
        return roleDao.findByIdRoles(roles);
    }

    @Override
    public List<Role> getRolesByUserId(Long id) {
        return roleDao.getRolesByUserId(id);
    }

}
