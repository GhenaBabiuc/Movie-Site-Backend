package com.example.service.movie.impl;

import com.example.dao.movie.FilmDao;
import com.example.model.movie.Film;
import com.example.model.movie.FilmFilter;
import com.example.model.movie.PagedData;
import com.example.service.movie.FilmService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DefaultFilmService implements FilmService {

    @Resource
    private FilmDao videoDao;

    @Override
    public PagedData getAllVideos(FilmFilter filmFilter, Integer start, Integer limit) {
        List<Film> films = videoDao.getAllFilms(filmFilter, start, limit);
        Long count = videoDao.countFilms(filmFilter);

        return new PagedData(count, films);
    }

    @Override
    public Film getFilmById(Long id) {
        return videoDao.getFilmById(id);
    }
}
