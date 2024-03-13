package com.example.dao.movie.impl;

import com.example.dao.movie.FilmDao;
import com.example.dao.movie.filter.FilmFilterUtil;
import com.example.model.movie.Film;
import com.example.model.movie.FilmFilter;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.List;

@Repository
public class DefaultFilmDao implements FilmDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Film> getAllFilms(FilmFilter filmFilter, Integer start, Integer limit) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Film> filmCriteriaQuery = criteriaBuilder.createQuery(Film.class).distinct(true);
        Root<Film> filmRoot = filmCriteriaQuery.from(Film.class);

        List<Predicate> predicates = FilmFilterUtil.buildsPredicates(criteriaBuilder, filmRoot, filmFilter);

        if (filmFilter.getValueOrder() != null && !filmFilter.getValueOrder().isEmpty()) {
            Path<Object> orderPath = filmRoot.get(filmFilter.getValueOrder());

            if ("desc".equalsIgnoreCase(filmFilter.getOrder())) {
                filmCriteriaQuery.orderBy(criteriaBuilder.desc(orderPath));
            } else {
                filmCriteriaQuery.orderBy(criteriaBuilder.asc(orderPath));
            }
        }

        filmCriteriaQuery.where(predicates.toArray(new Predicate[0]));

        return entityManager.createQuery(filmCriteriaQuery).setFirstResult(start).setMaxResults(limit).getResultList();
    }

    @Override
    public Long countFilms(FilmFilter filmFilter) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> filmCriteriaQuery = criteriaBuilder.createQuery(Long.class);
        Root<Film> filmRoot = filmCriteriaQuery.from(Film.class);
        filmCriteriaQuery.select(criteriaBuilder.count(filmRoot));

        List<Predicate> predicates = FilmFilterUtil.buildsPredicates(criteriaBuilder, filmRoot, filmFilter);

        filmCriteriaQuery.where(predicates.toArray(new Predicate[0]));

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
