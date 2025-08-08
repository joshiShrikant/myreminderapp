package com.example.reminderApp.service;

import com.example.reminderApp.entity.User;
import com.example.reminderApp.payload.LoginRequest;
import com.example.reminderApp.payload.UserDTO;
import com.example.reminderApp.repository.UserRepository;
import com.example.reminderApp.util.JwtTokenUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;


    //    @Override
    public ResponseEntity<?> registerUser(User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            return ResponseEntity.badRequest().body("Username already taken");
        }
        if (userRepository.existsByEmail(user.getEmail())) {
            return ResponseEntity.badRequest().body("Email already registered");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser = userRepository.save(user);

        return ResponseEntity.ok(savedUser);
    }

    //    @Override
    public ResponseEntity<?> login(LoginRequest loginRequest) {
        User user = userRepository.findByUsername(loginRequest.getUsername());
        if (user == null || !passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            return ResponseEntity.status(401).body("Invalid username or password");
        }

        String token = jwtTokenUtil.generateToken(user);
        Map<String, Object> response = new HashMap<>();
        response.put("user", user);
        response.put("token", token);

        return ResponseEntity.ok(response);
    }

    //    @Override
    public ResponseEntity<?> getProfile(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            return ResponseEntity.status(404).body("User not found");
        }
        Map<String, Object> response = new HashMap<>();

        if (user.getProfilePicture() != null) {
            response.put("profilePicture", user.getProfilePicture());
        }
        response.put("user", user);
        response.put("status", 200);
        response.put("message", "Profile loaded successfully.");
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<?> updateProfile(User updatedUser, String username) {
        User existingUser = userRepository.findByUsername(username);
        if (existingUser == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
        // Update fields
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setFirstName(updatedUser.getFirstName());
        existingUser.setLastName(updatedUser.getLastName());
        existingUser.setPhoneNumber(updatedUser.getPhoneNumber());
        existingUser.setAddress(updatedUser.getAddress());

        System.out.println("existingUser  "+existingUser);
        userRepository.save(existingUser);
        return ResponseEntity.ok(existingUser);
    }

    @Override
    public ResponseEntity<?> updateProfilePicture(Map<String, String> user, String username) {
        String base64Image = user.get("image");
        User updatedUser = userRepository.findByUsername(username);
        if (updatedUser == null) {
            return ResponseEntity.status(404).body("User not found");
//            throw new UsernameNotFoundException("User not found");
        }
        updatedUser.setProfilePicture(base64Image);
        userRepository.save(updatedUser);
        return ResponseEntity.ok(Map.of("message", "Profile picture updated successfully"));
    }

    private UserDTO mapToDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setProfilePicture(user.getProfilePicture());
        return dto;
    }

    private String extractTokenFromRequest(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            return header.substring(7);
        }
        return null;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
