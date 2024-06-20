package com.example.controller.user;

import com.example.model.user.dto.AuthDto;
import com.example.model.user.dto.UserRegistrationDto;
import com.example.model.user.exception.AppError;
import com.example.service.user.AuthService;
import com.example.service.user.UserService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.security.Principal;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private AuthService authService;

    @Resource
    private UserService userService;

    @PostMapping("/auth")
    public ResponseEntity<?> createAuthToken(@RequestBody AuthDto AuthDto, HttpServletResponse response) {
        return authService.createAuthToken(AuthDto, response);
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refreshAuthToken(HttpServletRequest request, HttpServletResponse response) {
        return authService.refreshAuthToken(request, response);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletResponse response) {
        return authService.logout(response);
    }

    @PostMapping("/registration")
    public ResponseEntity<?> createNewUser(@RequestBody UserRegistrationDto userRegistrationDto) {
        return authService.createNewUser(userRegistrationDto);
    }

    @GetMapping("/check-auth")
    public ResponseEntity<?> checkAuth(HttpServletRequest request) {
        return ResponseEntity.ok(authService.checkAuth(request));
    }

    @PostMapping("/activate")
    public ResponseEntity<?> activateAccount(@RequestBody String token) {
        return authService.activateAccount(token);
    }

    @GetMapping("/data")
    public ResponseEntity<?> getUserData(Principal principal) {
        return userService.findByUsername(principal.getName())
                .map(user -> {
                    user.setPassword(null);

                    return user;
                })
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/update-password")
    public ResponseEntity<?> updateUserPassword(@RequestBody String password, Principal principal) {
        return authService.updateUserPassword(password, principal.getName());
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestBody String email) {
        try {
            String decodedEmail = URLDecoder.decode(email, StandardCharsets.UTF_8);

            return authService.sendPasswordResetEmail(decodedEmail);
        } catch (Exception e) {
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), "Invalid email format"), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestParam String token, @RequestBody String newPassword) {
        return authService.resetPassword(token, newPassword);
    }
}