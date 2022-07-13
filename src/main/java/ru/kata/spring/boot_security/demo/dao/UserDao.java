package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;

public interface UserDao {

    List<User> getAllUsers();

    void delete(long id);

    User update(User user);

    void saveUser(User user);

    User getUserById(long id);

    User getByUserName(String username);
}
