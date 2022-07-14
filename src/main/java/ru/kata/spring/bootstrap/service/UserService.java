package ru.kata.spring.bootstrap.service;

import ru.kata.spring.bootstrap.models.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    void delete(long id);

    void update(User user);

    void saveUser(User user);

    User getUserById(long id);

    User getUserByName(String username);
}
