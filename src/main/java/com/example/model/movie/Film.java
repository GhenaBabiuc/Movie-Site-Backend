package com.example.model.movie;

import com.example.jackson.LocalDateDeserializer;
import com.example.jackson.LocalDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "films", schema = "movie")
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "cover_url")
    private String coverUrl;

    @Column(name = "title", nullable = false)
    private String title;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @Column(name = "release_date")
    private LocalDate releaseDate;

    @Column(name = "rating", precision = 2, scale = 1)
    private Double rating;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "trailer_embed_link")
    private String trailerEmbedLink;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "film_genres", schema = "movie", joinColumns = {@JoinColumn(name = "film_id")}, inverseJoinColumns = {@JoinColumn(name = "genre_id")})
    private Set<Genre> genres;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "film_actors", schema = "movie", joinColumns = {@JoinColumn(name = "film_id")}, inverseJoinColumns = {@JoinColumn(name = "actor_id")})
    private Set<Actor> actors;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "film_directors", schema = "movie", joinColumns = {@JoinColumn(name = "film_id")}, inverseJoinColumns = {@JoinColumn(name = "director_id")})
    private Set<Director> directors;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "film_writers", schema = "movie", joinColumns = {@JoinColumn(name = "film_id")}, inverseJoinColumns = {@JoinColumn(name = "writer_id")})
    private Set<Writer> writers;
}