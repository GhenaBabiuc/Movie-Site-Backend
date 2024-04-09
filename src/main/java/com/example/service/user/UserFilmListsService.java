package com.example.service.user;

import com.example.model.user.UserFilm;

import java.util.List;
import java.util.Optional;

public interface UserFilmListsService {
    List<UserFilm> getUserFilmLists(String username);

    void deleteFilmForFilmLists(Long filmId, String username);

    void addUserFilm(Long filmId, String status, String username);

    Optional<UserFilm> getUserFilm(Long filmId, String username);
}
