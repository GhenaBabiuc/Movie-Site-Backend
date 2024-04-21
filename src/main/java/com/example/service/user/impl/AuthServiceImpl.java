package com.example.service.user.impl;

import com.example.model.user.Role;
import com.example.model.user.User;
import com.example.model.user.dto.AuthDto;
import com.example.model.user.dto.UserRegistrationDto;
import com.example.model.user.exception.AppError;
import com.example.service.user.AuthService;
import com.example.service.user.RoleService;
import com.example.service.user.UserService;
import com.example.service.user.util.JwtTokenUtils;
import jakarta.annotation.Resource;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {

    @Resource
    private UserService userService;

    @Resource
    private JwtTokenUtils jwtTokenUtils;

    @Resource
    private AuthenticationManager authenticationManager;

    @Resource
    private RoleService roleService;

    @Lazy
    @Resource
    private PasswordEncoder passwordEncoder;

    @Override
    public ResponseEntity<?> createAuthToken(AuthDto authDto, HttpServletResponse response) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authDto.getUsername(), authDto.getPassword()));
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(new AppError(HttpStatus.UNAUTHORIZED.value(), "Incorrect login or password"), HttpStatus.UNAUTHORIZED);
        }

        UserDetails userDetails = userService.loadUserByUsername(authDto.getUsername());
        String accessToken = jwtTokenUtils.generateToken(userDetails);
        String refreshToken = jwtTokenUtils.generateRefreshToken(userDetails);

        Cookie accessTokenCookie = new Cookie("auth_token", accessToken);
        accessTokenCookie.setHttpOnly(true);
        accessTokenCookie.setSecure(true);
        accessTokenCookie.setPath("/");
        response.addCookie(accessTokenCookie);

        Cookie refreshTokenCookie = new Cookie("refresh_token", refreshToken);
        refreshTokenCookie.setHttpOnly(true);
        refreshTokenCookie.setSecure(true);
        refreshTokenCookie.setPath("/");
        refreshTokenCookie.setMaxAge(7 * 24 * 60 * 60);
        response.addCookie(refreshTokenCookie);

        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<?> refreshAuthToken(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        String refreshToken = null;

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("refresh_token".equals(cookie.getName())) {
                    refreshToken = cookie.getValue();
                    break;
                }
            }
        }

        if (refreshToken == null || !jwtTokenUtils.validateToken(refreshToken)) {
            return new ResponseEntity<>(new AppError(HttpStatus.UNAUTHORIZED.value(), "Invalid refresh token"), HttpStatus.UNAUTHORIZED);
        }

        String username = jwtTokenUtils.getUsername(refreshToken);
        UserDetails userDetails = userService.loadUserByUsername(username);
        String newAccessToken = jwtTokenUtils.generateToken(userDetails);

        Cookie accessTokenCookie = new Cookie("auth_token", newAccessToken);
        accessTokenCookie.setHttpOnly(true);
        accessTokenCookie.setSecure(true);
        accessTokenCookie.setPath("/");
        response.addCookie(accessTokenCookie);

        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<?> logout(HttpServletResponse response) {
        Cookie cookie = new Cookie("auth_token", null);
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setPath("/");
        cookie.setMaxAge(0);
        response.addCookie(cookie);

        Cookie refreshTokenCookie = new Cookie("refresh_token", null);
        refreshTokenCookie.setHttpOnly(true);
        refreshTokenCookie.setSecure(true);
        refreshTokenCookie.setPath("/");
        refreshTokenCookie.setMaxAge(0);
        response.addCookie(refreshTokenCookie);

        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<?> createNewUser(UserRegistrationDto userRegistrationDto) {
        if (userRegistrationDto.getUsername() == null || userRegistrationDto.getUsername().isEmpty() ||
                userRegistrationDto.getPassword() == null || userRegistrationDto.getPassword().isEmpty() ||
                userRegistrationDto.getConfirmPassword() == null || userRegistrationDto.getConfirmPassword().isEmpty() ||
                userRegistrationDto.getEmail() == null || userRegistrationDto.getEmail().isEmpty()) {
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), "All fields must be filled"), HttpStatus.BAD_REQUEST);
        }

        if (!userRegistrationDto.getPassword().equals(userRegistrationDto.getConfirmPassword())) {
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), "The passwords don't match"), HttpStatus.BAD_REQUEST);
        }

        if (userService.findByUsername(userRegistrationDto.getUsername()).isPresent()) {
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), "A user with the specified name already exists"), HttpStatus.BAD_REQUEST);
        }

        if (userService.findByEmail(userRegistrationDto.getEmail()).isPresent()) {
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), "A user with the specified email already exists"), HttpStatus.BAD_REQUEST);
        }

        User user = new User();
        Optional<Role> optionalRole = roleService.getUserRole();

        optionalRole.ifPresent(role -> {
            user.setUsername(userRegistrationDto.getUsername());
            user.setEmail(userRegistrationDto.getEmail());
            user.setPassword(passwordEncoder.encode(userRegistrationDto.getPassword()));
            user.setRoles(Set.of(role));

            userService.save(user);
        });

        return ResponseEntity.ok().build();
    }

    @Override
    public Boolean checkAuth(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        String authToken = null;

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("auth_token".equals(cookie.getName())) {
                    authToken = cookie.getValue();
                    break;
                }
            }
        }

        return authToken != null && jwtTokenUtils.validateToken(authToken);
    }
}
