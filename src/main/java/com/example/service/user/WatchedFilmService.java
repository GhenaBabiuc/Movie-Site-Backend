package com.example.service.user;

import com.example.model.user.WatchedFilm;

import java.util.List;

public interface WatchedFilmService {
    List<WatchedFilm> getUserFilmLists(String username);

    void deleteFilmForFilmLists(Long filmId, String username);

    void addUserFilm(Long filmId, String username);
}
