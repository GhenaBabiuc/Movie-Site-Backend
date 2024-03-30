package com.example.service.movie;

import com.example.model.movie.Film;
import com.example.model.movie.FilmFilter;
import org.springframework.data.domain.Page;

public interface FilmService {
    Page<Film> getAllVideos(FilmFilter filmFilter, Integer pageNumber, Integer pageSize);

    Film getFilmById(Long id);
}
