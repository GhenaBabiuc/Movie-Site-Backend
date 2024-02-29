package com.example.video.controller;

import com.example.video.model.movie.Genre;
import com.example.video.service.GenreService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/genres")
public class GenreController {

    @Resource
    private GenreService genreService;

    @GetMapping("/all")
    public @ResponseBody List<Genre> getAllGenres() {
        return genreService.getAllGenres();
    }
}
