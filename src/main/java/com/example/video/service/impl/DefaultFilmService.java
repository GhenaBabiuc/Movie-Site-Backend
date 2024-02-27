package com.example.video.service.impl;

import com.example.video.dao.FilmDao;
import com.example.video.model.FilmFilter;
import com.example.video.model.PagedData;
import com.example.video.model.movie.Film;
import com.example.video.service.FilmService;
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
