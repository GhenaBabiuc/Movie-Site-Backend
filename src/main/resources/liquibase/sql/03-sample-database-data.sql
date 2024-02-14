--liquibase formatted sql

--changeset gbabiuc:insert-films-table splitStatements:false
INSERT INTO movie.films (title, release_date, director, rating, description, genre)
VALUES ('The Shawshank Redemption', '1994-09-23', 'Frank Darabont', 9.3,
        'Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency.',
        'Drama'),
       ('The Godfather', '1972-03-24', 'Francis Ford Coppola', 9.2,
        'The aging patriarch of an organized crime dynasty transfers control of his clandestine empire to his reluctant son.',
        'Crime, Drama');

--changeset gbabiuc:insert-actors-table splitStatements:false
INSERT INTO movie.actors (full_name, birth_date, biography)
VALUES ('Tim Robbins', '1958-10-16',
        'Timothy Francis Robbins is an American actor, screenwriter, director, producer, and musician.'),
       ('Morgan Freeman', '1937-06-01', 'Morgan Freeman is an American actor, director, and narrator.'),
       ('Marlon Brando', '1924-04-03', 'Marlon Brando Jr. was an American actor and film director.'),
       ('Al Pacino', '1940-04-25', 'Alfredo James Pacino is an American actor and filmmaker.');

--changeset gbabiuc:insert-film_actors-table splitStatements:false
INSERT INTO movie.film_actors (film_id, actor_id)
VALUES ((SELECT id FROM movie.films WHERE title = 'The Shawshank Redemption'),
        (SELECT id FROM movie.actors WHERE full_name = 'Tim Robbins')),
       ((SELECT id FROM movie.films WHERE title = 'The Shawshank Redemption'),
        (SELECT id FROM movie.actors WHERE full_name = 'Morgan Freeman')),
       ((SELECT id FROM movie.films WHERE title = 'The Godfather'),
        (SELECT id FROM movie.actors WHERE full_name = 'Marlon Brando')),
       ((SELECT id FROM movie.films WHERE title = 'The Godfather'),
        (SELECT id FROM movie.actors WHERE full_name = 'Al Pacino'));
