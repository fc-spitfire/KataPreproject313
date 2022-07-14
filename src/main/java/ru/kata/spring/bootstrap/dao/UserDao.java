package ru.kata.spring.bootstrap.dao;

import ru.kata.spring.bootstrap.models.User;

import java.util.List;

public interface UserDao {

    List<User> getAllUsers();

    void delete(long id);

    User update(User user);

    void saveUser(User user);

    User getUserById(long id);

    User getByUserName(String username);
}
