package com.example.video.service;

import com.example.video.model.FilmFilter;
import com.example.video.model.PagedData;
import com.example.video.model.movie.Film;

public interface FilmService {
    PagedData getAllVideos(FilmFilter filmFilter, Integer start, Integer limit);

    Film getFilmById(Long id);
}
