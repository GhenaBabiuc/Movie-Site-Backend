package com.example.video.dao.impl;

import com.example.video.dao.FilmDao;
import com.example.video.model.movie.Film;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class DefaultFilmDao implements FilmDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Film> getAllFilms() {
        return entityManager.createQuery("SELECT f FROM Film f", Film.class).getResultList();
    }
}
