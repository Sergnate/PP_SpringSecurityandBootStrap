package ru.kata.spring.boot_security.demo.controllers;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.forms.UserCreateApi;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api")
public class RestController {

    private final UserService userService;

    public RestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public User getOneUser(@PathVariable("id") Long id) {
        return userService.findById(id);
    }

    @PostMapping()
    public UserCreateApi createNewUser(@RequestBody UserCreateApi user) {

        User newUser = User.builder()
                .username(user.getUsername())
                .userGender(user.getUserGender())
                .nickname(user.getUserNickname())
                .password(user.getPassword())
                .build();

        newUser.setRoles(userService.findRolesByName(user.getRoles()));
        userService.saveUser(newUser);
        return user;
    }

    @PutMapping
    public UserCreateApi updateUser(@RequestBody UserCreateApi user) {
        User newUser = User.builder()
                .id(user.getId())
                .username(user.getUsername())
                .userGender(user.getUserGender())
                .nickname(user.getUserNickname())
                .password(user.getPassword())
                .build();
        newUser.setRoles(userService.findRolesByName(user.getRoles()));
        userService.update(newUser);
        return user;
    }
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUserById(id);
        return "OK";
    }
}
