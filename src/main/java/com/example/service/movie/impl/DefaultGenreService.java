package com.example.service.movie.impl;

import com.example.dao.movie.GenreDao;
import com.example.model.movie.Genre;
import com.example.service.movie.GenreService;
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
