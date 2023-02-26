package ru.kata.spring.boot_security.demo.dao;


import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.User;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@Transactional
public class UserDaoImp implements UserDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> readUsers() {
        return entityManager.createQuery("select u from User u").getResultList();
    }

    @Override
    public void saveUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public User readUserID(long id) {
        return entityManager.find(User.class,id);
    }

    @Override
    public void updateUser(User user) {
        entityManager.merge(user);
    }

    @Override
    public void deleteUser(long id) {
        User user = entityManager.find(User.class, id);
        entityManager.remove(user);
    }

    @Override
    public User findByUsername(String username) {
        TypedQuery<User> paramname = entityManager.createQuery("select u from User u left join fetch u.roles where u.username=:paramname", User.class)
                .setParameter("paramname", username);
        User user = paramname.getResultList().stream().findFirst().orElse(null);
        return user;
    }
}
