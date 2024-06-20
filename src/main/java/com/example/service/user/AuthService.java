package com.example.service.user;

import com.example.model.user.dto.AuthDto;
import com.example.model.user.dto.UserRegistrationDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    ResponseEntity<?> createAuthToken(AuthDto authDto, HttpServletResponse response);

    ResponseEntity<?> refreshAuthToken(HttpServletRequest request, HttpServletResponse response);

    ResponseEntity<?> logout(HttpServletResponse response);

    ResponseEntity<?> createNewUser(UserRegistrationDto userRegistrationDto);

    Boolean checkAuth(HttpServletRequest request);

    ResponseEntity<?> activateAccount(String token);

    ResponseEntity<?> updateUserPassword(String password, String username);

    ResponseEntity<?> sendPasswordResetEmail(String email);

    ResponseEntity<?> resetPassword(String token, String newPassword);
}
