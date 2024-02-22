package com.example.video.dao.filter;

import com.example.video.model.FilmFilter;
import com.example.video.model.movie.Film;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class FilmFilterUtil {
    public static List<Predicate> buildsPredicates(CriteriaBuilder criteriaBuilder, Root<Film> filmRoot, FilmFilter filmFilter) {

        List<Predicate> predicates = new ArrayList<>();

        if (StringUtils.isNotBlank(filmFilter.getTitle())) {
            String title = filmFilter.getTitle().toLowerCase();
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(filmRoot.get("title")), "%" + title + "%"));
        }

        return predicates;
    }
}
