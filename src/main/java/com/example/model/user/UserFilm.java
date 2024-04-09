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
@Table(name = "user_films", schema = "users")
public class UserFilm {

    @JsonIgnore
    @EmbeddedId
    private UserFilmId id;

    @MapsId("filmId")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "film_id")
    private Film film;

    @Column(name = "added_on")
    private LocalDate addedOn;

    @Column(name = "status")
    private String status;
}
