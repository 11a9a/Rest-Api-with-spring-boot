package com.example.secandlog.iservice;

import com.example.secandlog.dto.UserDTO;
import com.example.secandlog.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IUser extends UserDetailsService {
    User save(UserDTO userDTO);

    List<User> getAllUsers();

    User getUserById(Long id);

    User updateUserRole(Long id, String role);

    void deleteUserById(Long id);
}
