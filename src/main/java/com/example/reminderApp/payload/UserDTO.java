package com.example.reminderApp.payload;


import lombok.Data;

@Data
public class UserDTO {
    private Long id;
    private String username;
    private String email;
    private String profilePicture;
}