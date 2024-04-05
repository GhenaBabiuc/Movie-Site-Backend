package com.example.model.user;

import com.example.model.movie.Film;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_watched_films", schema = "users")
public class WatchedFilm {

    @JsonIgnore
    @EmbeddedId
    private WatchedFilmId id;

    @MapsId("filmId")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "film_id")
    private Film film;

    @Column(name = "watched_on")
    private LocalDate watchedOn;
}
