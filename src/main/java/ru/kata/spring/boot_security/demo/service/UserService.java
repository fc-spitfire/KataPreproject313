package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    void delete(long id);

    void update(User user);

    void saveUser(User user);

    User getUserById(long id);

    User getUserByName(String username);
}
