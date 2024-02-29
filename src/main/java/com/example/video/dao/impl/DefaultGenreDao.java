package com.example.video.dao.impl;

import com.example.video.dao.GenreDao;
import com.example.video.model.movie.Genre;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class DefaultGenreDao implements GenreDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Genre> getAllGenres() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Genre> genreCriteriaQuery = criteriaBuilder.createQuery(Genre.class);
        Root<Genre> genreRoot = genreCriteriaQuery.from(Genre.class);

        genreCriteriaQuery.select(genreRoot);

        return entityManager.createQuery(genreCriteriaQuery).getResultList();
    }
}
