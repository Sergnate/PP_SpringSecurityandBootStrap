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

    private UserRepository UserRepo;
    private RoleRepository roleRepository;
    private  PasswordEncoder passwordEncoder;


    @Autowired
    public UserServiceImpl(UserRepository UserRepo, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.UserRepo = UserRepo;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void addUser(User user) {
         user.setPassword(passwordEncoder.encode(user.getPassword()));
         UserRepo.save(user);
    }
    @Override
    public User getUserById(Long id) {
        return UserRepo.findById(id).get();
    }
    @Override
    public List<User> listOfUsers() {
        return UserRepo.findAll();
    }
    @Override
    public void deleteUser(Long id) {
        UserRepo.deleteById(id);
    }


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = UserRepo.findByUsername(username);
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                getAuthorities(user.getRoles()));
    }

    private static Collection<? extends GrantedAuthority> getAuthorities(Collection<Role> roles) {
        return roles.stream().map(r -> new SimpleGrantedAuthority(r.getAuthority()))
                .collect(Collectors.toSet());
    }

    public User findByUsername(String username) {
        return UserRepo.findByUsername(username);
    }

    public List<Role> listRoles() {
        return roleRepository.findAll();
    }

    public void saveAndFlush(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        UserRepo.saveAndFlush(user);
    }

    public Set<Role> getRoles (ArrayList<Long> roles){
        return roleRepository.findByIdIn(roles);
    }

    public ArrayList <Long> rolesToId (Set<Role> roles){
        ArrayList<Long> rolesId = new ArrayList<>();
        for (Role role:roles){
            rolesId.add(Long.valueOf(role.getName()));
        }
        return rolesId;
    }

    public User findByUserName(String userName) {
        return UserRepo.findByUsername(userName);
    }
}
