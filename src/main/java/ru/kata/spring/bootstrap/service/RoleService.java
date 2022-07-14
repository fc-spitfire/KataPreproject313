package ru.kata.spring.bootstrap.service;

import ru.kata.spring.bootstrap.models.Role;

import java.util.List;

public interface RoleService {
    List<Role> getAll();

    Role getRoleByName(String name);

    void saveRole(Role role);
}
