package com.example.video.dao;

import com.example.video.model.FilmFilter;
import com.example.video.model.movie.Film;

import java.util.List;

public interface FilmDao {
    List<Film> getAllFilms(FilmFilter filmFilter, Integer start, Integer limit);

    Long countFilms(FilmFilter filmFilter);

    Film getFilmById(Long id);
}
