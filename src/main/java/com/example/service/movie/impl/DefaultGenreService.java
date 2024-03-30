package com.example.service.movie.impl;

import com.example.repository.movie.GenreRepository;
import com.example.model.movie.Genre;
import com.example.service.movie.GenreService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultGenreService implements GenreService {

    @Resource
    private GenreRepository genreRepository;

    @Override
    public List<Genre> getAllGenres() {
        return genreRepository.findAll();
    }
}
