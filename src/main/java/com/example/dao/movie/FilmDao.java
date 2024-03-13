package com.example.dao.movie;

import com.example.model.movie.Film;
import com.example.model.movie.FilmFilter;

import java.util.List;

public interface FilmDao {
    List<Film> getAllFilms(FilmFilter filmFilter, Integer start, Integer limit);

    Long countFilms(FilmFilter filmFilter);

    Film getFilmById(Long id);
}
