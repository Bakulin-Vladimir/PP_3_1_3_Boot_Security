package ru.kata.spring.boot_security.demo.dao;




import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserDao {
    void saveUser(User user);

    User readUserID(long id);

    List<User> readUsers();

    void updateUser(User user);

    void deleteUser(long id);

    User findByUsername(String username);
}
