package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface RoleDao {
    void saveRole(Role role);

    Role readRoleID(long id);

    List<Role> readRoles();

    void updateRole(Role role);

    void deleteRole(long id);
}
