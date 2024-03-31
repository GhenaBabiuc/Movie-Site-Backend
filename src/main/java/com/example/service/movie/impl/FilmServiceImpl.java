package com.example.service.movie.impl;

import com.example.model.movie.Film;
import com.example.model.movie.FilmFilter;
import com.example.model.movie.Genre;
import com.example.repository.movie.FilmRepository;
import com.example.service.movie.FilmService;
import jakarta.annotation.Resource;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FilmServiceImpl implements FilmService {

    @Resource
    private FilmRepository filmRepository;

    @Override
    public Page<Film> getAllVideos(FilmFilter filmFilter, Integer pageNumber, Integer pageSize) {
        Sort sort = Sort.unsorted();

        if (filmFilter.getValueOrder() != null && !filmFilter.getValueOrder().isEmpty()) {
            Sort.Direction direction = "desc".equalsIgnoreCase(filmFilter.getOrder()) ? Sort.Direction.DESC : Sort.Direction.ASC;
            sort = Sort.by(direction, filmFilter.getValueOrder());
            sort = sort.and(Sort.by(Sort.Direction.ASC, "id"));
        }

        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sort);
        Specification<Film> spec = buildFilmSpecification(filmFilter);

        return filmRepository.findAll(spec, pageRequest);
    }

    private Specification<Film> buildFilmSpecification(FilmFilter filmFilter) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filmFilter.getTitle() != null) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("title")), "%" + filmFilter.getTitle().toLowerCase() + "%"));
            }

            if (filmFilter.getGenres() != null && !filmFilter.getGenres().isEmpty()) {
                Join<Film, Genre> genreJoin = root.join("genres");
                CriteriaBuilder.In<String> genreInClause = criteriaBuilder.in(genreJoin.get("name").as(String.class));
                filmFilter.getGenres().forEach(genre -> genreInClause.value(genre.toLowerCase()));
                predicates.add(genreInClause);
            }

            query.distinct(true);

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

    @Override
    public Film getFilmById(Long id) {
        return filmRepository.findById(id).get();
    }
}
