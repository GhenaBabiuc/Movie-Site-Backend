package com.example.model.user.dto;

import lombok.Data;

@Data
public class UserRegistrationDto {
    private String username;
    private String password;
    private String confirmPassword;
    private String email;
}
