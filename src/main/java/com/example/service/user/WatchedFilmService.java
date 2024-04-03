package com.example.service.user;

import com.example.model.user.WatchedFilm;

import java.util.List;

public interface WatchedFilmService {
    List<WatchedFilm> findWatchedFilmsByUserId(Long userId);
}
