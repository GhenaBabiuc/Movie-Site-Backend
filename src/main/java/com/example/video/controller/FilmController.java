package com.example.video.controller;

import com.example.video.model.FilmFilter;
import com.example.video.model.PagedData;
import com.example.video.model.movie.Film;
import com.example.video.service.FilmService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/films")
public class FilmController {

    @Resource
    private FilmService filmService;

    @GetMapping("/all")
    public @ResponseBody PagedData getAllVideos(@ModelAttribute FilmFilter filmFilter,
                                                @RequestParam(required = false, defaultValue = "0") Integer start,
                                                @RequestParam(required = false, defaultValue = "102") Integer limit) {
        return filmService.getAllVideos(filmFilter, start, limit);
    }

    @GetMapping("/{id}")
    public @ResponseBody Film getFilmById(@PathVariable Long id) {
        return filmService.getFilmById(id);
    }
}
