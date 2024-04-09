package com.example.service.user.impl;

import com.example.model.movie.Film;
import com.example.model.user.User;
import com.example.model.user.UserFilm;
import com.example.model.user.UserFilmId;
import com.example.repository.user.UserFilmListsRepository;
import com.example.service.movie.FilmService;
import com.example.service.user.UserFilmListsService;
import com.example.service.user.UserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserFilmListsServiceImpl implements UserFilmListsService {

    @Resource
    private UserFilmListsRepository userFilmListsRepository;

    @Resource
    private FilmService filmService;

    @Resource
    private UserService userService;

    @Override
    public List<UserFilm> getUserFilmLists(String username) {
        return userService.findByUsername(username)
                .map(user -> userFilmListsRepository.findByIdUserId(user.getId()))
                .orElse(Collections.emptyList());
    }

    @Override
    public void deleteFilmForFilmLists(Long filmId, String username) {
        Optional<User> optionalUser = userService.findByUsername(username);
        optionalUser.ifPresent(user -> userFilmListsRepository.deleteById(new UserFilmId(user.getId(), filmId)));
    }

    @Override
    public void addUserFilm(Long filmId, String status, String username) {
        Optional<User> optionalUser = userService.findByUsername(username);
        Optional<Film> optionalFilm = filmService.getById(filmId);

        if (optionalUser.isPresent() && optionalFilm.isPresent() && status != null) {
            UserFilm userFilm = new UserFilm();
            userFilm.setId(new UserFilmId(optionalUser.get().getId(), filmId));
            userFilm.setAddedOn(LocalDate.now());
            userFilm.setFilm(optionalFilm.get());
            userFilm.setStatus(status);
            userFilmListsRepository.save(userFilm);
        }
    }

    @Override
    public Optional<UserFilm> getUserFilm(Long filmId, String username) {
        Optional<User> optionalUser = userService.findByUsername(username);

        return optionalUser.flatMap(user -> userFilmListsRepository.findById(new UserFilmId(user.getId(), filmId)));
    }
}
