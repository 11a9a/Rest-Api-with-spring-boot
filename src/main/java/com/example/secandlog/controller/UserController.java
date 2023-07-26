package com.example.secandlog.controller;

import com.example.secandlog.iservice.IUser;
import com.example.secandlog.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class UserController {
    private final IUser iUser;
//    @Autowired
    private final BCryptPasswordEncoder passwordEncoder;

    @GetMapping("/users")
    public String listUsers(Model model) {
        model.addAttribute("users", iUser.getAllUsers());
        return "users";
    }

    @GetMapping("/users/edit/{id}")
    public String editUserRole(@PathVariable Long id, Model model) {
        model.addAttribute("user", iUser.getUserById(id));
        return "edit_user_role";
    }

    @PostMapping("/users/{id}")
    public String updateUserRole(@PathVariable Long id, @ModelAttribute("user") User user) {
        iUser.updateUserRole(id, user.getRole().toString());
        return "redirect:/users";
    }

    @GetMapping("/users/{id}")
    public String deleteUser(@PathVariable Long id) {
        iUser.deleteUserById(id);
        return "redirect:/users";
    }
}
