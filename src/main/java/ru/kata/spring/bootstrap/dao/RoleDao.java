package ru.kata.spring.bootstrap.dao;

import ru.kata.spring.bootstrap.models.Role;

import java.util.List;

public interface RoleDao {
    List<Role> getAll();

    Role getRoleByName(String name);

    void saveRole(Role role);
}
