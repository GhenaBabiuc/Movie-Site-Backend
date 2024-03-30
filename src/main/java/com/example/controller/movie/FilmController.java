package com.example.controller.movie;

import com.example.model.movie.Film;
import com.example.model.movie.FilmFilter;
import com.example.service.movie.FilmService;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/films")
public class FilmController {

    @Resource
    private FilmService filmService;

    @GetMapping("/all")
    public @ResponseBody Page<Film> getAllVideos(@ModelAttribute FilmFilter filmFilter,
                                                 @RequestParam(required = false, defaultValue = "0") Integer pageNumber,
                                                 @RequestParam(required = false, defaultValue = "102") Integer pageSize) {
        return filmService.getAllVideos(filmFilter, pageNumber, pageSize);
    }

    @GetMapping("/{id}")
    public @ResponseBody Film getFilmById(@PathVariable Long id) {
        return filmService.getFilmById(id);
    }
}
