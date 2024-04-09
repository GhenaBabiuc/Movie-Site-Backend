package com.example.controller.user;

import com.example.service.user.WatchedFilmService;
import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/user-films-lists")
public class UserFilmsListsController {

    @Resource
    private WatchedFilmService watchedFilmService;

    @GetMapping("/all")
    public ResponseEntity<?> getUserFilmLists(Principal principal) {
        return ResponseEntity.ok(watchedFilmService.getUserFilmLists(principal.getName()));
    }

    @DeleteMapping("/delete/{filmId}")
    public ResponseEntity<?> deleteFilmForFilmLists(@PathVariable Long filmId, Principal principal) {
        watchedFilmService.deleteFilmForFilmLists(filmId, principal.getName());

        return ResponseEntity.ok().build();
    }

    @PostMapping("/add/{filmId}")
    public ResponseEntity<?> addUserFilm(@PathVariable Long filmId, Principal principal) {
        watchedFilmService.addUserFilm(filmId, principal.getName());

        return ResponseEntity.ok().build();
    }
}
