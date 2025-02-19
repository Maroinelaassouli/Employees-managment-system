package com.ests.ems.controllers;

import com.ests.ems.entities.User;
import com.ests.ems.entities.LoginRequest;
import com.ests.ems.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest) {
        // Validate input
        if (loginRequest.getUsername() == null || loginRequest.getPassword() == null) {
            return "Username and Password cannot be empty!";
        }

        // Find user by username
        Optional<User> user = userRepository.findByUsername(loginRequest.getUsername());

        // Check if user exists and password matches
        if (user.isPresent() && user.get().getPassword().equals(loginRequest.getPassword())) {
            return "Login successful!";
        } else {
            return "Invalid username or password!";
        }
    }
}
