package com.example.service.user.impl;

import com.example.model.user.User;
import com.example.model.user.WatchedFilm;
import com.example.model.user.WatchedFilmId;
import com.example.repository.user.WatchedFilmRepository;
import com.example.service.movie.FilmService;
import com.example.service.user.UserService;
import com.example.service.user.WatchedFilmService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WatchedFilmServiceImpl implements WatchedFilmService {

    @Resource
    private WatchedFilmRepository watchedFilmRepository;

    @Resource
    private FilmService filmService;

    @Resource
    private UserService userService;

    @Override
    public List<WatchedFilm> getUserFilmLists(String username) {
        Optional<User> user = userService.findByUsername(username);
        List<WatchedFilm> watchedFilms = new ArrayList<>();
        user.ifPresent(value -> watchedFilms.addAll(watchedFilmRepository.findByIdUserId(value.getId())));

        return watchedFilms;
    }

    @Override
    public void deleteFilmForFilmLists(Long filmId, String username) {
        Optional<User> optionalUser = userService.findByUsername(username);
        optionalUser.ifPresent(user -> watchedFilmRepository.deleteById(new WatchedFilmId(user.getId(), filmId)));
    }

    @Override
    public void addUserFilm(Long filmId, String username) {
        Optional<User> optionalUser = userService.findByUsername(username);
        optionalUser.ifPresent(user -> {
            WatchedFilm watchedFilm = new WatchedFilm();
            watchedFilm.setId(new WatchedFilmId(user.getId(), filmId));
            watchedFilm.setWatchedOn(LocalDate.now());
            watchedFilm.setFilm(filmService.getFilmById(filmId));
            watchedFilmRepository.save(watchedFilm);
        });
    }
}
