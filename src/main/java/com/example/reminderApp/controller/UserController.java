package com.example.reminderApp.controller;

import com.example.reminderApp.entity.User;
import com.example.reminderApp.payload.LoginRequest;
import com.example.reminderApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin(origins = "http://localhost:4200") // for Angular
public class UserController {


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        return userService.registerUser(user);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        return userService.login(loginRequest);
    }

    @GetMapping("/profile")
    public ResponseEntity<?> getUserProfile(Authentication authentication) {
        String username = authentication.getName();
        return userService.getProfile(username);
    }

    @PutMapping("/profile")
    public ResponseEntity<?> updateProfile(@RequestBody User user, Principal principal) {
        return userService.updateProfile(user, principal.getName());
    }

    @PutMapping("/profile/picture")
    public ResponseEntity<?> uploadProfilePicture(@RequestBody Map<String, String> request, Principal principal) {
        return userService.updateProfilePicture(request, principal.getName());
    }

}
