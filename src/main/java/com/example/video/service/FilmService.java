package com.example.video.service;

import com.example.video.model.FilmFilter;
import com.example.video.model.movie.Film;

import java.util.List;

public interface FilmService {
    List<Film> getAllVideos(FilmFilter filmFilter);

    Film getFilmById(Long id);
}
