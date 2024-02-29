package com.example.video.service.impl;

import com.example.video.dao.GenreDao;
import com.example.video.model.movie.Genre;
import com.example.video.service.GenreService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DefaultGenreService implements GenreService {

    @Resource
    private GenreDao genreDao;

    @Override
    public List<Genre> getAllGenres() {
        return genreDao.getAllGenres();
    }
}
