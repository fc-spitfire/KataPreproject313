package ru.kata.spring.bootstrap.models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kata.spring.bootstrap.service.RoleService;
import ru.kata.spring.bootstrap.service.UserService;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataInit {
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public DataInit(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostConstruct
    public void createUsers() {

        Role roleAdmin = new Role("ROLE_ADMIN");
        Role roleUser = new Role("ROLE_USER");

        roleService.saveRole(roleAdmin);
        roleService.saveRole(roleUser);

        List<Role> rolesAdmin = new ArrayList<>(List.of(roleAdmin, roleUser));
        List<Role> rolesUser = new ArrayList<>(List.of(roleUser));

        userService.saveUser(new User("John", "Doe", 23, "New York", "admin@mail.ru", "12345", rolesAdmin));
        userService.saveUser(new User("Jane", "Doe", 28, "New Jersey", "user@mail.ru", "12345", rolesUser));
        userService.saveUser(new User("Jake", "Doe", 21, "Austin", "user1@mail.ru", "12345", rolesUser));
        userService.saveUser(new User("Jim", "Doe", 22, "New Orleans", "user2@mail.ru", "12345", rolesUser));
        userService.saveUser(new User("Joe", "Doe", 39, "Raleigh", "user3@mail.ru", "12345", rolesUser));
        userService.saveUser(new User("Jim", "Doe", 19, "Oklahoma City", "guest@mail.ru", "12345", rolesUser));
    }
}
