package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private UserService userService;

    private RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping()
    public String getUsers(Model model) {
        //Получим всех людей из DAO и передадим на отображение в представлении
        model.addAttribute("usersList", userService.readUsers());
        return "users";
    }

    @GetMapping("/{id}")
    public String showIdUser(@PathVariable("id") long id, Model model) {
        //Получим одного человека по id из DAO и передадим на отображение в представление
        model.addAttribute("user", userService.readUserId(id));
        return "show_user";
    }

    @GetMapping("/new")
    public String newUser(Model model) {
        Set<Role> roles = roleService.readRoles();
        model.addAttribute("usernew", new User());
        model.addAttribute("allRoles", roles);
        return "new";
    }

    @PostMapping()
    public String saveUser(@ModelAttribute("usernew") @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "new";
        }
        if (user.getRoles().stream().anyMatch(r -> r.getName().equals("ROLE_ADMIN"))) {
            user.setRoles(getRole());
        }
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") long id, Model model) {
        Set<Role> roles = roleService.readRoles();
        model.addAttribute("allRoles", roles);
        model.addAttribute("user", userService.readUserId(id));
        return "edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") @Valid User user, BindingResult bindingResult,
                         @PathVariable("id") long id) {
        if (bindingResult.hasErrors()) {
            return "edit";
        }
        if (user.getRoles().stream().anyMatch(r -> r.getName().equals("ROLE_ADMIN"))) {
            user.setRoles(getRole());
        }
        userService.updateUser(user);
        return "redirect:/admin";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }

    private Set<Role> getRole() {
        Set<Role> setRole = new HashSet<>();
        setRole.add(new Role("ROLE_ADMIN"));
        setRole.add(new Role("ROLE_USER"));
        return setRole;
    }
}
