package com.example.controller.movie;

import com.example.model.movie.Film;
import com.example.model.movie.FilmFilter;
import com.example.service.movie.FilmService;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/films")
public class FilmController {

    @Resource
    private FilmService filmService;

    @GetMapping("/all")
    public ResponseEntity<Page<Film>> getAll(@ModelAttribute FilmFilter filmFilter,
                                             @RequestParam(required = false, defaultValue = "0") Integer pageNumber,
                                             @RequestParam(required = false, defaultValue = "102") Integer pageSize) {
        return ResponseEntity.ok(filmService.getAll(filmFilter, pageNumber, pageSize));
    }

    @GetMapping("/{filmId}")
    public ResponseEntity<Film> getById(@PathVariable Long filmId) {
        return filmService.getById(filmId).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());

    }
}
