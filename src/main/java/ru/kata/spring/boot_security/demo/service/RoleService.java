package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.Role;

import java.util.List;

public interface RoleService {
    void saveRole(Role role);

    Role readRoleID(long id);

    List<Role> readRoles();

    void updateRole(Role role);

    void deleteRole(long id);
}
