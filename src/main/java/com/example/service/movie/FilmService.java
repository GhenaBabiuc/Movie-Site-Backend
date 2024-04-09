package com.example.service.movie;

import com.example.model.movie.Film;
import com.example.model.movie.FilmFilter;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface FilmService {
    Page<Film> getAll(FilmFilter filmFilter, Integer pageNumber, Integer pageSize);

    Optional<Film> getById(Long id);
}
