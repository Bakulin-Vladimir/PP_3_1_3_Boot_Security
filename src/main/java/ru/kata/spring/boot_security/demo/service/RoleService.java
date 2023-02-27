package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.Role;

import java.util.Set;

public interface RoleService {
    void saveRole(Role role);

    Role readRoleID(long id);

    Set<Role> readRoles();

    void updateRole(Role role);

    void deleteRole(long id);
}
