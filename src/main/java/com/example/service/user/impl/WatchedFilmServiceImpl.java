package com.example.service.user.impl;

import com.example.model.user.WatchedFilm;
import com.example.repository.user.WatchedFilmRepository;
import com.example.service.user.WatchedFilmService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WatchedFilmServiceImpl implements WatchedFilmService {

    @Resource
    private WatchedFilmRepository watchedFilmRepository;

    @Override
    public List<WatchedFilm> findWatchedFilmsByUserId(Long userId) {
        return watchedFilmRepository.findByIdUserId(userId);
    }
}
