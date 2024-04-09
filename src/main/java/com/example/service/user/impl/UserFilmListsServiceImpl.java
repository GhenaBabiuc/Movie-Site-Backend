package com.example.service.user.impl;

import com.example.model.movie.Film;
import com.example.model.user.User;
import com.example.model.user.WatchedFilm;
import com.example.model.user.WatchedFilmId;
import com.example.repository.user.UserFilmListsRepository;
import com.example.service.movie.FilmService;
import com.example.service.user.UserService;
import com.example.service.user.WatchedFilmService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserFilmListsServiceImpl implements WatchedFilmService {

    @Resource
    private UserFilmListsRepository userFilmListsRepository;

    @Resource
    private FilmService filmService;

    @Resource
    private UserService userService;

    @Override
    public List<WatchedFilm> getUserFilmLists(String username) {
        return userService.findByUsername(username)
                .map(user -> userFilmListsRepository.findByIdUserId(user.getId()))
                .orElse(Collections.emptyList());
    }

    @Override
    public void deleteFilmForFilmLists(Long filmId, String username) {
        Optional<User> optionalUser = userService.findByUsername(username);
        optionalUser.ifPresent(user -> userFilmListsRepository.deleteById(new WatchedFilmId(user.getId(), filmId)));
    }

    @Override
    public void addUserFilm(Long filmId, String username) {
        Optional<User> optionalUser = userService.findByUsername(username);
        Optional<Film> optionalFilm = filmService.getById(filmId);

        if (optionalUser.isPresent() && optionalFilm.isPresent()) {
            WatchedFilm watchedFilm = new WatchedFilm();
            watchedFilm.setId(new WatchedFilmId(optionalUser.get().getId(), filmId));
            watchedFilm.setWatchedOn(LocalDate.now());
            watchedFilm.setFilm(optionalFilm.get());
            userFilmListsRepository.save(watchedFilm);
        }
    }

    @Override
    public Optional<WatchedFilm> getUserFilmById(Long filmId, String username) {
        Optional<User> optionalUser = userService.findByUsername(username);

        return optionalUser.flatMap(user -> userFilmListsRepository.findById(new WatchedFilmId(user.getId(), filmId)));
    }
}
