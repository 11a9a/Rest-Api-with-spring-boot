package com.example.secandlog.controller;

import com.example.secandlog.dto.UserDTO;
import com.example.secandlog.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class RegistrationController {
    private final UserService userService;

    @ModelAttribute ("user")
    public UserDTO userRegistrationDTO(){
        return new UserDTO();
    }

    @GetMapping("/registration")
    public String showRegistrationForm() {
        return "registration";
    }

    @PostMapping("/registration")
    public String registerUserAccount(@ModelAttribute("user") UserDTO userDto) {
        userService.save(userDto);
        return "redirect:/login?success";
    }
}
