package ru.kata.spring.bootstrap.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.bootstrap.models.Role;
import ru.kata.spring.bootstrap.models.User;
import ru.kata.spring.bootstrap.service.RoleService;
import ru.kata.spring.bootstrap.service.UserService;

import java.security.Principal;
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
    public String showAllUsers(Model model, Principal principal) {
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("user", userService.getUserByName(principal.getName()));
        model.addAttribute("rolesList", roleService.getAll());
        model.addAttribute("createUser", new User());
        return "index";
    }

//    @GetMapping("/{id}/edit")
//    public String edit(@PathVariable("id") long id, Model model) {
//        model.addAttribute("user", userService.getUserById(id));
//        model.addAttribute("rolesList", roleService.getAll());
//        return "edit";
//    }

    @PatchMapping("/update/{id}")
    public String update(@ModelAttribute("user") User user, @PathVariable("id") long id, @RequestParam String[] roles1) {
        List<Role> roleList = new ArrayList<>();
        for (String s : roles1) {
            roleList.add(roleService.getRoleByName(s));
        }
        user.setRoles(roleList);
        userService.update(user);
        return "redirect:/admin";
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable("id") long id) {
        userService.delete(id);
        return "redirect:/admin";
    }

//    @GetMapping("/new")
//    public String newUser(Model model) {
//        model.addAttribute("user", new User());
//        model.addAttribute("rolesList", roleService.getAll());
//        return "new";
//    }

    @PostMapping("/admin/save")
    public String createUser(@ModelAttribute("user") User user, @RequestParam String[] roles1) {
        List<Role> roleList = new ArrayList<>();
        for (String s : roles1) {
            roleList.add(roleService.getRoleByName(s));
        }
        user.setRoles(roleList);
        userService.saveUser(user);
        return "redirect:/index";
    }
}

