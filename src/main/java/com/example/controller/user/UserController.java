package com.example.controller.user;

import com.example.model.user.User;
import com.example.model.user.WatchedFilm;
import com.example.model.user.dto.AuthDto;
import com.example.model.user.dto.UserRegistrationDto;
import com.example.service.user.AuthService;
import com.example.service.user.UserService;
import com.example.service.user.WatchedFilmService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private AuthService authService;

    @Resource
    private UserService userService;

    @Resource
    private WatchedFilmService watchedFilmService;

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

    @GetMapping("/films")
    public List<WatchedFilm> userDataWatchedFilms(Principal principal) {
        Optional<User> user = userService.findByUsername(principal.getName());
        return watchedFilmService.findWatchedFilmsByUserId(user.get().getId());
    }
}