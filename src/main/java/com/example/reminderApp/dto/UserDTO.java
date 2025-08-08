package com.example.reminderApp.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
    private String username;
    private String email;
    private String profilePicture; // base64 with data URI prefix
    // Add more fields if needed

    // Constructors
    public UserDTO() {}

    public UserDTO(String username, String email, String profilePicture) {
        this.username = username;
        this.email = email;
        this.profilePicture = profilePicture;
    }
}
