package com.example.controller.user;

import com.example.model.user.dto.AuthDto;
import com.example.model.user.dto.UserRegistrationDto;
import com.example.service.user.AuthService;
import com.example.service.user.UserService;
import com.example.service.user.util.JwtTokenUtils;
import io.jsonwebtoken.JwtException;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private AuthService authService;

    @Resource
    private UserService userService;

    @Resource
    private JwtTokenUtils jwtTokenUtils;

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
        try {
            String username = jwtTokenUtils.getUsername(token);
            userService.activateUser(username);
            return ResponseEntity.ok("Account activated successfully.");
        } catch (JwtException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid or expired token.");
        }
    }
}