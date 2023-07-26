package com.example.secandlog.service;

import com.example.secandlog.dto.UserDTO;
import com.example.secandlog.iservice.IUser;
import com.example.secandlog.model.Role;
import com.example.secandlog.model.User;
import com.example.secandlog.repository.UserRepository;
//import jakarta.annotation.PostConstruct;
import javax.annotation.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserService implements IUser {
    private final UserRepository userRepository;

    @Autowired
    private  BCryptPasswordEncoder passwordEncoder;

    @Override
    public User save(UserDTO userDTO) {
        User user = new User(userDTO.getFirstName(),
                userDTO.getLastName(), userDTO.getEmail(),
                passwordEncoder.encode(userDTO.getPassword()), new Role("ROLE_USER"));
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public User updateUserRole(Long id, String role) {
        User user = userRepository.findById(id).get();
        user.setRole(new Role(role));
        return userRepository.save(user);
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        if(user==null){
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(),
                user.getPassword(), mapRoleToAuthorities(user.getRole()));
    }

    private Collection<? extends GrantedAuthority> mapRoleToAuthorities(Role role) {
        return Collections.singleton(new SimpleGrantedAuthority(role.getName())) ;
    }

    @PostConstruct
    public void createAdminUser() {
        User admin = new User("admin", "admin", "admin@uni.com",
                passwordEncoder.encode("admin"), new Role("ROLE_ADMIN"));
        userRepository.save(admin);
    }

    @PostConstruct
    public void createDefaultUser() {
        User default_user = new User("default", "user", "user@uni.com",
                passwordEncoder.encode("user"), new Role("ROLE_USER"));
        userRepository.save(default_user);
    }
}
