package com.example.controller.movie;

import com.example.model.movie.FilmFilter;
import com.example.model.movie.dto.FilmDataDto;
import com.example.model.user.UserFilm;
import com.example.service.movie.FilmService;
import com.example.service.user.UserFilmListsService;
import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/films")
public class FilmController {

    @Resource
    private FilmService filmService;

    @Resource
    private UserFilmListsService userFilmListsService;

    @GetMapping("/all")
    public ResponseEntity<?> getAll(@ModelAttribute FilmFilter filmFilter,
                                    @RequestParam(required = false, defaultValue = "0") Integer pageNumber,
                                    @RequestParam(required = false, defaultValue = "102") Integer pageSize) {
        return ResponseEntity.ok(filmService.getAll(filmFilter, pageNumber, pageSize));
    }

    @GetMapping("/{filmId}")
    public ResponseEntity<?> getFilmData(@PathVariable Long filmId, Principal principal) {
        return filmService.getById(filmId)
                .map(film -> {
                    String username = principal != null ? principal.getName() : null;
                    String status = userFilmListsService.getUserFilm(filmId, username)
                            .map(UserFilm::getStatus)
                            .orElse("Not Watched");
                    FilmDataDto filmDataDto = FilmDataDto.builder()
                            .film(film)
                            .status(status)
                            .build();
                    return ResponseEntity.ok(filmDataDto);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
