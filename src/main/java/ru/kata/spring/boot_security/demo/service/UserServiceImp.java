package ru.kata.spring.boot_security.demo.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.UserDao;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;


import java.util.ArrayList;
import java.util.List;


@Service
public class UserServiceImp implements UserService{
    private UserDao userDao;

    private RoleService roleService;

    private PasswordEncoder passwordEncoder;

    public UserServiceImp(UserDao userDao, RoleService roleService, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
        defaultUserSave();
    }

    @Override
    public List<User> readUsers() {
        return userDao.readUsers();
    }

    @Override
    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.saveUser(user);
    }

    @Override
    public User readUserId(long id) {
        return userDao.readUserID(id);
    }

    @Override
    public void updateUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.updateUser(user);
    }

    @Override
    public void deleteUser(long id) {
        userDao.deleteUser(id);
    }

    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    public void defaultUserSave(){
        List<Role> list1 = new ArrayList<>();
        list1.add(new Role("ROLE_USER"));
        saveUser(new User("user"
                ,"Петр"
                ,"IT"
                ,"petr123@mail.ru"
                ,(byte)35,"user"
                ,list1));
        List<Role> list2 = new ArrayList<>();
        list2.add(new Role("ROLE_USER"));
        list2.add(new Role("ROLE_ADMIN"));
        saveUser(new User("admin"
                ,"Олег"
                ,"IT"
                ,"oleg555@mail.ru"
                ,(byte)35,"admin"
                ,list2));
    }

}
