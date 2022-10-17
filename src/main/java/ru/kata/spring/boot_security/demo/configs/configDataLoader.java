package ru.kata.spring.boot_security.demo.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;
import ru.kata.spring.boot_security.demo.service.UserService;
import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class configDataLoader {
    @Autowired
    UserService userService;
    @Autowired
    PasswordEncoder bCryptPasswordEncoder;
    @Autowired
    RoleRepository roleRepository;


    @PostConstruct
    void init() {
        Role userRole = new Role("ROLE_ADMIN");
        Role adminRole = new Role( "ROLE_USER");

        roleRepository.save(userRole);
        roleRepository.save(adminRole);

        List<Role> roles = new ArrayList<>();

        roles.add(userRole);
        roles.add(adminRole);

        User user = new User("admin", "man",  "БигБосс", "admin", roles);

        userService.saveUser(user);

    }
}