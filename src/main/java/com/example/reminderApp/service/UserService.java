package com.example.reminderApp.service;


import com.example.reminderApp.entity.User;
import com.example.reminderApp.payload.LoginRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.security.Principal;
import java.util.Map;

public interface UserService {
    ResponseEntity<?> registerUser(User user);
    ResponseEntity<?> login(LoginRequest loginRequest);
    ResponseEntity<?> getProfile(String username);
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
    ResponseEntity<?> updateProfile(User user, String username);
    ResponseEntity<?> updateProfilePicture(Map<String, String> user, String username);
}
