package com.example.controller.user;

import com.example.service.user.UserFilmListsService;
import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/user-films-lists")
public class UserFilmsListsController {

    @Resource
    private UserFilmListsService userFilmListsService;

    @GetMapping("/all")
    public ResponseEntity<?> getUserFilmLists(Principal principal) {
        return ResponseEntity.ok(userFilmListsService.getUserFilmLists(principal.getName()));
    }

    @DeleteMapping("/delete/{filmId}")
    public ResponseEntity<?> deleteFilmForFilmLists(@PathVariable Long filmId, Principal principal) {
        userFilmListsService.deleteFilmForFilmLists(filmId, principal.getName());

        return ResponseEntity.ok().build();
    }

    @PostMapping("/add/{filmId}")
    public ResponseEntity<?> addUserFilm(@PathVariable Long filmId, @RequestParam String status, Principal principal) {
        userFilmListsService.addUserFilm(filmId, status, principal.getName());

        return ResponseEntity.ok().build();
    }
}
