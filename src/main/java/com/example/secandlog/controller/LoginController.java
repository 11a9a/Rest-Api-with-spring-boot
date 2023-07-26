package com.example.secandlog.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    @GetMapping("/login")
    public String login(Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            // User is already authenticated, redirect to index.html
            return "index.html";
        }

        // User is not authenticated, display login.html
        return "login";
    }

    @GetMapping("/")
    public String home(Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            // User is already authenticated, redirect to index.html
            return "index.html";
        }

        // User is not authenticated, display login.html
        return "redirect:/login";
    }
}
