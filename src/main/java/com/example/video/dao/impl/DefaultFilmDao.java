package com.example.video.dao.impl;

import com.example.video.dao.FilmDao;
import com.example.video.model.movie.Film;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class DefaultFilmDao implements FilmDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Film> getAllFilms() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Film> filmCriteriaQuery = criteriaBuilder.createQuery(Film.class);
        Root<Film> filmRoot = filmCriteriaQuery.from(Film.class);

//        filmRoot.fetch("genres", JoinType.LEFT);

        filmCriteriaQuery.orderBy(criteriaBuilder.desc(filmRoot.get("rating")));

        return entityManager.createQuery(filmCriteriaQuery).getResultList();
    }

    @Override
    public Film getFilmById(Long id) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Film> criteriaQuery = criteriaBuilder.createQuery(Film.class);
        Root<Film> filmRoot = criteriaQuery.from(Film.class);

//        filmRoot.fetch("genres", JoinType.LEFT);

        criteriaQuery.where(criteriaBuilder.equal(filmRoot.get("id"), id));

        return entityManager.createQuery(criteriaQuery).getSingleResult();
    }
}
