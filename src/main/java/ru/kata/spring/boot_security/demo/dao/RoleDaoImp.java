package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class RoleDaoImp implements RoleDao{
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public void saveRole(Role role) {
        entityManager.persist(role);
    }

    @Override
    public Role readRoleID(long id) {
        return entityManager.find(Role.class,id);
    }

    @Override
    public List<Role> readRoles() {
        return entityManager.createQuery("select r from Role r").getResultList();
    }

    @Override
    public void updateRole(Role role) {
        entityManager.merge(role);

    }

    @Override
    public void deleteRole(long id) {
        Role role = entityManager.find(Role.class, id);
        entityManager.remove(role);

    }
}
