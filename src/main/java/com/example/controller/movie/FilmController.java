package com.example.controller.movie;

import com.example.model.movie.Film;
import com.example.model.movie.FilmFilter;
import com.example.model.movie.PagedData;
import com.example.service.movie.FilmService;
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
