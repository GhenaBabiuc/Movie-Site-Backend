package com.example.service.movie;

import com.example.model.movie.Film;
import com.example.model.movie.FilmFilter;
import com.example.model.movie.PagedData;

public interface FilmService {

    PagedData getAllVideos(FilmFilter filmFilter, Integer start, Integer limit);

    Film getFilmById(Long id);
}
