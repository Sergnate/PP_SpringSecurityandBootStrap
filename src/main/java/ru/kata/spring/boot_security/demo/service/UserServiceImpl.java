package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;
import ru.kata.spring.boot_security.demo.repository.UserRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements  UserService, UserDetailsService {

    private UserRepository userRepo;
    private RoleRepository roleRepository;
    private  PasswordEncoder passwordEncoder;


    @Autowired
    public UserServiceImpl(UserRepository userRepo, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void addUser(User user) {
         user.setPassword(passwordEncoder.encode(user.getPassword()));
         userRepo.save(user);
    }
    @Override
    public User getUserById(Long id) {
        return userRepo.findById(id).get();
    }
    @Override
    public List<User> listOfUsers() {
        return userRepo.findAll();
    }
    @Override
    public void deleteUser(Long id) {
        userRepo.deleteById(id);
    }


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username);
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                getAuthorities(user.getRoles()));
    }

    private static Collection<? extends GrantedAuthority> getAuthorities(Collection<Role> roles) {
        return roles.stream().map(r -> new SimpleGrantedAuthority(r.getAuthority()))
                .collect(Collectors.toSet());
    }

    public User findByUsername(String username) {
        return userRepo.findByUsername(username);
    }

    public List<Role> listRoles() {
        return roleRepository.findAll();
    }


}
