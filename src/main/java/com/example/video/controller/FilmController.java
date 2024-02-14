package com.example.video.controller;

import com.example.video.model.movie.Film;
import com.example.video.service.FilmService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/films")
public class FilmController {

    @Resource
    private FilmService filmService;

    @GetMapping("/allFilms")
    public ResponseEntity<List<Film>> getAllVideos() {
        List<Film> videos = filmService.getAllVideos();
        return ResponseEntity.ok(videos);
    }
}
