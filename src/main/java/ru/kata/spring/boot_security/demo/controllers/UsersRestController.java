package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;
import ru.kata.spring.boot_security.demo.service.UserServiceImpl;

import java.security.Principal;
import java.util.List;

@RestController
public class UsersRestController {

    private final RoleRepository roleRepository;
    private final UserServiceImpl userService;

    @Autowired
    public UsersRestController(RoleRepository roleRepository, UserServiceImpl userService) {
        this.roleRepository = roleRepository;
        this.userService = userService;
    }

    public RoleRepository getRoleRepository() {
        return roleRepository;
    }

    public UserServiceImpl getUserService() {
        return userService;
    }

    @GetMapping("/api/getroles")
    private List<Role> allRoles() {
        return userService.listRoles();
    }


    @GetMapping("/api/principal")
    public User getPrincipalInfo(Principal principal) {
        return userService.findByUserName(principal.getName());
    }

    @GetMapping("/api/{id}")
    public User findOneUser(@PathVariable long id) {
        User user = userService.getUserById(id);
        return user;
    }

    @PostMapping("/api")
    public void addNewUser(@RequestBody User user) {
        userService.addUser(user);
    }

    @GetMapping("/api")
    public ResponseEntity<List<User>> findAllUsers() {
        return ResponseEntity.ok().body(userService.listOfUsers());
    }

    @PutMapping("/api/{id}")
    public void updateUser(@RequestBody User user) {
        userService.saveAndFlush(user);
    }

    @DeleteMapping("/api/{id}")
    public void deleteUser(@PathVariable long id) {
        userService.deleteUser(id);
    }

}
