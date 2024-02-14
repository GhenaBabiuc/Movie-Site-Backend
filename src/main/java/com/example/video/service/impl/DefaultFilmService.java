package com.example.video.service.impl;

import com.example.video.dao.FilmDao;
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
    public List<Film> getAllVideos() {
        return videoDao.getAllFilms();
    }
}
