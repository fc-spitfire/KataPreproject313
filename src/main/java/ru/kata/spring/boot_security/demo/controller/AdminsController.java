package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminsController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminsController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping()
    public String showAllUsers(Model model) {
        model.addAttribute("user", userService.getAllUsers());
        return "index";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        model.addAttribute("rolesList", roleService.getAll());
        return "edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") User user, @PathVariable("id") long id, @RequestParam(defaultValue = "ROLE_USER") String[] roles1) {
        List<Role> roleList = new ArrayList<>();
        for (String s : roles1) {
            roleList.add(roleService.getRoleByName(s));
        }
        user.setRoles(roleList);
        userService.update(user);
        return "redirect:/admin";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") long id) {
        userService.delete(id);
        return "redirect:/admin";
    }

    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("rolesList", roleService.getAll());
        return "new";
    }

    @PostMapping
    public String createUser(@ModelAttribute("user") User user, @RequestParam(defaultValue = "ROLE_USER") String[] roles1) {
        List<Role> roleList = new ArrayList<>();
        for (String s : roles1) {
            roleList.add(roleService.getRoleByName(s));
        }
        user.setRoles(roleList);
        userService.saveUser(user);
        return "redirect:/admin";
    }
}

