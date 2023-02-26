package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.RoleDao;
import ru.kata.spring.boot_security.demo.model.Role;

import java.util.List;

@Service
public class RoleServiceImp implements RoleService{
    private RoleDao roleDao;
    public RoleServiceImp(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public void saveRole(Role role) {
        roleDao.saveRole(role);
    }

    @Override
    public Role readRoleID(long id) {
        return roleDao.readRoleID(id);
    }

    @Override
    public List<Role> readRoles() {
        return roleDao.readRoles();
    }

    @Override
    public void updateRole(Role role) {
        roleDao.updateRole(role);

    }

    @Override
    public void deleteRole(long id) {
        roleDao.deleteRole(id);

    }
}
