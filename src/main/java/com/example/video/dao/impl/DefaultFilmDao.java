package com.example.video.dao.impl;

import com.example.video.dao.FilmDao;
import com.example.video.dao.filter.FilmFilterUtil;
import com.example.video.model.FilmFilter;
import com.example.video.model.movie.Film;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class DefaultFilmDao implements FilmDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Film> getAllFilms(FilmFilter filmFilter, Integer start, Integer limit) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Film> filmCriteriaQuery = criteriaBuilder.createQuery(Film.class);
        Root<Film> filmRoot = filmCriteriaQuery.from(Film.class);

        List<Predicate> predicates = FilmFilterUtil.buildsPredicates(criteriaBuilder, filmRoot, filmFilter);

        filmCriteriaQuery.where(predicates.toArray(new Predicate[predicates.size()]));
        filmCriteriaQuery.orderBy(
                criteriaBuilder.desc(criteriaBuilder.coalesce(filmRoot.get("rating"), 0)),
                criteriaBuilder.asc(filmRoot.get("id"))
        );

        return entityManager.createQuery(filmCriteriaQuery).setFirstResult(start).setMaxResults(limit).getResultList();
    }

    @Override
    public Long countFilms(FilmFilter filmFilter) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> filmCriteriaQuery = criteriaBuilder.createQuery(Long.class);
        Root<Film> filmRoot = filmCriteriaQuery.from(Film.class);
        filmCriteriaQuery.select(criteriaBuilder.count(filmRoot));

        List<Predicate> predicates = FilmFilterUtil.buildsPredicates(criteriaBuilder, filmRoot, filmFilter);

        filmCriteriaQuery.where(predicates.toArray(new Predicate[predicates.size()]));

        return entityManager.createQuery(filmCriteriaQuery).getSingleResult();
    }

    @Override
    public Film getFilmById(Long id) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Film> criteriaQuery = criteriaBuilder.createQuery(Film.class);
        Root<Film> filmRoot = criteriaQuery.from(Film.class);

        criteriaQuery.where(criteriaBuilder.equal(filmRoot.get("id"), id));

        return entityManager.createQuery(criteriaQuery).getSingleResult();
    }
}
