package com.example.service.movie.impl;

import com.example.model.movie.Genre;
import com.example.repository.movie.GenreRepository;
import com.example.service.movie.GenreService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {

    @Resource
    private GenreRepository genreRepository;

    @Override
    public List<Genre> getAll() {
        return genreRepository.findAll();
    }
}
