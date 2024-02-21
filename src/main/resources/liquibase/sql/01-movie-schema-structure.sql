--liquibase formatted sql

--changeset gbabiuc:create-movie-schema splitStatements:false
CREATE SCHEMA IF NOT EXISTS movie;

--changeset gbabiuc:create-films-table splitStatements:false
CREATE TABLE IF NOT EXISTS movie.films
(
    id           BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    title        VARCHAR(255) NOT NULL,
    release_date DATE,
    director     VARCHAR(255),
    rating       DECIMAL(2, 1),
    description  TEXT,
    genre        VARCHAR(100)
);

--changeset gbabiuc:create-actors-table splitStatements:false
CREATE TABLE IF NOT EXISTS movie.actors
(
    id         BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    full_name  VARCHAR(255) NOT NULL,
    birth_date DATE,
    biography  TEXT
);

--changeset gbabiuc:create-film_actors-table splitStatements:false
CREATE TABLE IF NOT EXISTS movie.film_actors
(
    film_id  BIGINT NOT NULL,
    actor_id BIGINT NOT NULL,
    PRIMARY KEY (film_id, actor_id),
    FOREIGN KEY (film_id) REFERENCES movie.films (id),
    FOREIGN KEY (actor_id) REFERENCES movie.actors (id)
);

--changeset gbabiuc:drop-genre-column-from-films-table splitStatements:false
ALTER TABLE movie.films
    DROP COLUMN IF EXISTS genre;

--changeset gbabiuc:create-genres-table splitStatements:false
CREATE TABLE IF NOT EXISTS movie.genres
(
    id          BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    name        VARCHAR(100) NOT NULL,
    description TEXT
);

--changeset gbabiuc:create-film_genres-table splitStatements:false
CREATE TABLE IF NOT EXISTS movie.film_genres
(
    film_id  BIGINT NOT NULL,
    genre_id BIGINT NOT NULL,
    PRIMARY KEY (film_id, genre_id),
    FOREIGN KEY (film_id) REFERENCES movie.films (id),
    FOREIGN KEY (genre_id) REFERENCES movie.genres (id)
);

--changeset gbabiuc:adding-cover_ur-to-films-table splitStatements:false
ALTER TABLE movie.films
    ADD COLUMN cover_url VARCHAR(255);
