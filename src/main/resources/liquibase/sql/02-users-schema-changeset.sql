--liquibase formatted sql

--changeset gbabiuc:create-user-schema splitStatements:false
CREATE SCHEMA IF NOT EXISTS users;

--changeset gbabiuc:create-users-table splitStatements:false
CREATE TABLE users.users
(
    id       BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL
);

--changeset gbabiuc:create-roles-table splitStatements:false
CREATE TABLE users.roles
(
    id   BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

--changeset gbabiuc:create-user_roles-table splitStatements:false
CREATE TABLE users.user_roles
(
    user_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES users.users (id),
    FOREIGN KEY (role_id) REFERENCES users.roles (id)
);

--changeset gbabiuc:insert-default-roles-to-roles-table splitStatements:false
INSERT INTO users.roles
VALUES (1, 'ROLE_USER'),
       (2, 'ROLE_ADMIN');

--changeset gbabiuc:create-user_watched_films-table splitStatements:false
CREATE TABLE IF NOT EXISTS users.user_watched_films
(
    user_id    BIGINT NOT NULL,
    film_id    BIGINT NOT NULL,
    watched_on DATE   NOT NULL,
    PRIMARY KEY (user_id, film_id),
    FOREIGN KEY (user_id) REFERENCES users.users (id),
    FOREIGN KEY (film_id) REFERENCES movie.films (id)
);

--changeset gbabiuc:set-unique-username splitStatements:false
ALTER TABLE users.users
    ADD UNIQUE (username);

--changeset gbabiuc:set-unique-role_name splitStatements:false
ALTER TABLE users.roles
    ADD UNIQUE (name);

--changeset gbabiuc:add-email-column-to-users-table splitStatements:false
ALTER TABLE users.users
    ADD email VARCHAR(255) UNIQUE;

--changeset gbabiuc:rename-user_watched_films-to-user_films splitStatements:false
ALTER TABLE users.user_watched_films
    RENAME TO user_films;

--changeset gbabiuc:add-status-column-to-user_films-table splitStatements:false
ALTER TABLE users.user_films
    add status VARCHAR(100) NOT NULL DEFAULT 'Watched';

--changeset gbabiuc:rename-column-watched_on-from-user_films-to-added_on splitStatements:false
ALTER TABLE users.user_films
    RENAME COLUMN watched_on to added_on;
