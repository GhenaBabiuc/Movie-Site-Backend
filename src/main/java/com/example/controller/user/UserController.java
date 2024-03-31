package com.example.controller.user;

import com.example.model.user.dto.AuthDto;
import com.example.model.user.dto.UserRegistrationDto;
import com.example.service.user.AuthService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private AuthService authService;

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

    @GetMapping("/info")
    public Principal userData(Principal principal) {
        return principal;
    }
}