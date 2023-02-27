package ru.kata.spring.boot_security.demo.service;


import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService {
    void saveUser(User user);

    List<User> readUsers();

    User readUserId(long id);

    void updateUser(User user);

    void deleteUser(long id);

    User findByUsername(String username);
}
