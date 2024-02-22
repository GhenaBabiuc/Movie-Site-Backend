package com.example.video.controller;

import com.example.video.model.FilmFilter;
import com.example.video.model.movie.Film;
import com.example.video.service.FilmService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/films")
public class FilmController {

    @Resource
    private FilmService filmService;

    @GetMapping("/all")
    public @ResponseBody List<Film> getAllVideos(FilmFilter filmFilter) {
        return filmService.getAllVideos(filmFilter);
    }

    @GetMapping("/{id}")
    public @ResponseBody Film getFilmById(@PathVariable Long id) {
        return filmService.getFilmById(id);
    }
}
