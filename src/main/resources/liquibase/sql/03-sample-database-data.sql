--liquibase formatted sql

--changeset gbabiuc:insert-films-table splitStatements:false
INSERT INTO movie.films (title, release_date, director, rating, description, cover_url)
VALUES ('The Shawshank Redemption', '1994-09-23', 'Frank Darabont', 9.3,
        'Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency.',
        'https://m.media-amazon.com/images/M/MV5BMDYwYTdkZGUtOWRiYS00OGYxLTkyZGUtZmEwMzk4MDYyMGM5XkEyXkFqcGdeQXVyMTQ2MjgxNTE4._V1_FMjpg_UY3000_.jpg'),
       ('The Godfather', '1972-03-24', 'Francis Ford Coppola', 9.2,
        'The aging patriarch of an organized crime dynasty transfers control of his clandestine empire to his reluctant son.',
        'https://m.media-amazon.com/images/M/MV5BM2MyNjYxNmUtYTAwNi00MTYxLWJmNWYtYzZlODY3ZTk3OTFlXkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_QL75_UY414_CR6,0,280,414_.jpg'),
       ('Inception', '2010-07-16', 'Christopher Nolan', 8.8,
        'A thief who steals corporate secrets through the use of dream-sharing technology is given the inverse task of planting an idea into the mind of a CEO.',
        'https://m.media-amazon.com/images/M/MV5BMjAxMzY3NjcxNF5BMl5BanBnXkFtZTcwNTI5OTM0Mw@@._V1_QL75_UX285_CR0,0,285,422_.jpg')
        ,
       ('Pulp Fiction', '1994-10-14', 'Quentin Tarantino', 8.9,
        'The lives of two mob hitmen, a boxer, a gangster and his wife, and a pair of diner bandits intertwine in four tales of violence and redemption.',
        'https://m.media-amazon.com/images/M/MV5BNGNhMDIzZTUtNTBlZi00MTRlLWFjM2ItYzViMjE3YzI5MjljXkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_QL75_UY414_CR2,0,280,414_.jpg'),
       ('Fight Club', '1999-10-15', 'David Fincher', 8.8,
        'An insomniac office worker and a devil-may-care soapmaker form an underground fight club that evolves into much more.',
        'https://m.media-amazon.com/images/M/MV5BMmEzNTkxYjQtZTc0MC00YTVjLTg5ZTEtZWMwOWVlYzY0NWIwXkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_QL75_UX280_CR0,3,280,414_.jpg'),
       ('Forrest Gump', '1994-07-06', 'Robert Zemeckis', 8.8,
        'The presidencies of Kennedy and Johnson, the Vietnam War, the Watergate scandal and other historical events unfold from the perspective of an Alabama man with an IQ of 75.',
        'https://m.media-amazon.com/images/M/MV5BNWIwODRlZTUtY2U3ZS00Yzg1LWJhNzYtMmZiYmEyNmU1NjMzXkEyXkFqcGdeQXVyMTQxNzMzNDI@._V1_QL75_UY414_CR3,0,280,414_.jpg'),
       ('The Matrix', '1999-03-31', 'Lana Wachowski, Lilly Wachowski', 8.7,
        'A computer hacker learns about the true nature of reality and his role in the war against its controllers.',
        'https://m.media-amazon.com/images/M/MV5BNzQzOTk3OTAtNDQ0Zi00ZTVkLWI0MTEtMDllZjNkYzNjNTc4L2ltYWdlXkEyXkFqcGdeQXVyNjU0OTQ0OTY@._V1_QL75_UX280_CR0,3,280,414_.jpg'),
       ('The Lord of the Rings: The Fellowship of the Ring', '2001-12-19', 'Peter Jackson', 8.8,
        'A meek Hobbit and eight companions set out on a journey to destroy the powerful One Ring and save Middle-earth from the Dark Lord Sauron.',
        'https://m.media-amazon.com/images/M/MV5BN2EyZjM3NzUtNWUzMi00MTgxLWI0NTctMzY4M2VlOTdjZWRiXkEyXkFqcGdeQXVyNDUzOTQ5MjY@._V1_QL75_UX285_CR0,1,285,422_.jpg'),
       ('Star Wars: Episode V - The Empire Strikes Back', '1980-05-21', 'Irvin Kershner', 8.7,
        'After the Rebels are brutally overpowered by the Empire on the ice planet Hoth, Luke Skywalker begins Jedi training with Yoda.',
        'https://m.media-amazon.com/images/M/MV5BYmU1NDRjNDgtMzhiMi00NjZmLTg5NGItZDNiZjU5NTU4OTE0XkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_QL75_UX285_CR0,11,285,422_.jpg'),
       ('The Godfather Part III', '1990-12-25', 'Francis Ford Coppola', 7.6,
        'In the midst of trying to legitimize his business dealings, an aging mafia don seeks to vow for a life free of crime but is pulled back by the threats against his family.',
        'https://m.media-amazon.com/images/M/MV5BNWFlYWY2YjYtNjdhNi00MzVlLTg2MTMtMWExNzg4NmM5NmEzXkEyXkFqcGdeQXVyMDk5Mzc5MQ@@._V1_QL75_UX285_CR0,0,285,422_.jpg');

--changeset gbabiuc:insert-actors-table splitStatements:false
INSERT INTO movie.actors (full_name, birth_date, biography)
VALUES ('Uma Thurman', '1970-04-29',
        'Uma Thurman is an American actress known for her work in Quentin Tarantino films.'),
       ('Brad Pitt', '1963-12-18', 'Brad Pitt is an American actor and film producer.'),
       ('Edward Norton', '1969-08-18', 'Edward Norton is an American actor and filmmaker.'),
       ('Tom Hanks', '1956-07-09', 'Tom Hanks is an American actor and filmmaker.'),
       ('Keanu Reeves', '1964-09-02', 'Keanu Reeves is a Canadian actor known for his role in The Matrix.'),
       ('Carrie-Anne Moss', '1967-08-21',
        'Carrie-Anne Moss is a Canadian actress known for her role of Trinity in The Matrix.'),
       ('Elijah Wood', '1981-01-28',
        'Elijah Wood is an American actor known for his role as Frodo Baggins in The Lord of the Rings trilogy.'),
       ('Ian McKellen', '1939-05-25', 'Sir Ian McKellen is an English actor.'),
       ('Harrison Ford', '1942-07-13', 'Harrison Ford is an American actor, aviator, and environmental activist.'),
       ('Mark Hamill', '1951-09-25', 'Mark Hamill is an American actor, voice actor, and writer.'),
       ('Al Pacino', '1940-04-25',
        'Al Pacino is an American actor and filmmaker, known for playing Michael Corleone in The Godfather trilogy.'),
       ('Leonardo DiCaprio', '1974-11-11',
        'Leonardo DiCaprio is an acclaimed American actor known for his roles in various blockbuster films.'),
       ('Joseph Gordon-Levitt', '1981-02-17',
        'Joseph Gordon-Levitt is an American actor and filmmaker who started acting at a young age.'),
       ('Tim Robbins', '1958-10-16',
        'Timothy Francis Robbins is an American actor, screenwriter, director, producer, and musician.'),
       ('Morgan Freeman', '1937-06-01', 'Morgan Freeman is an American actor, director, and narrator.'),
       ('Marlon Brando', '1924-04-03', 'Marlon Brando Jr. was an American actor and film director.');

--changeset gbabiuc:insert-film_actors-table splitStatements:false
INSERT INTO movie.genres (name)
VALUES ('Crime'),
       ('Drama'),
       ('Sci-Fi'),
       ('Thriller'),
       ('Romance'),
       ('Comedy'),
       ('Epic'),
       ('Action'),
       ('Adventure'),
       ('Fantasy');

--changeset gbabiuc:insert-genres-table splitStatements:false
INSERT INTO movie.film_actors (film_id, actor_id)
VALUES ((SELECT id FROM movie.films WHERE title = 'The Shawshank Redemption'),
        (SELECT id FROM movie.actors WHERE full_name = 'Tim Robbins')),
       ((SELECT id FROM movie.films WHERE title = 'The Shawshank Redemption'),
        (SELECT id FROM movie.actors WHERE full_name = 'Morgan Freeman')),
       ((SELECT id FROM movie.films WHERE title = 'The Godfather'),
        (SELECT id FROM movie.actors WHERE full_name = 'Marlon Brando')),
       ((SELECT id FROM movie.films WHERE title = 'The Godfather'),
        (SELECT id FROM movie.actors WHERE full_name = 'Al Pacino')),
       ((SELECT id FROM movie.films WHERE title = 'Inception'),
        (SELECT id FROM movie.actors WHERE full_name = 'Leonardo DiCaprio')),
       ((SELECT id FROM movie.films WHERE title = 'Inception'),
        (SELECT id FROM movie.actors WHERE full_name = 'Joseph Gordon-Levitt')),
       ((SELECT id FROM movie.films WHERE title = 'Pulp Fiction'),
        (SELECT id FROM movie.actors WHERE full_name = 'Uma Thurman')),
       ((SELECT id FROM movie.films WHERE title = 'Fight Club'),
        (SELECT id FROM movie.actors WHERE full_name = 'Brad Pitt')),
       ((SELECT id FROM movie.films WHERE title = 'Fight Club'),
        (SELECT id FROM movie.actors WHERE full_name = 'Edward Norton')),
       ((SELECT id FROM movie.films WHERE title = 'Forrest Gump'),
        (SELECT id FROM movie.actors WHERE full_name = 'Tom Hanks')),
       ((SELECT id FROM movie.films WHERE title = 'The Matrix'),
        (SELECT id FROM movie.actors WHERE full_name = 'Keanu Reeves')),
       ((SELECT id FROM movie.films WHERE title = 'The Matrix'),
        (SELECT id FROM movie.actors WHERE full_name = 'Carrie-Anne Moss'));

--changeset gbabiuc:insert-film_genres-table splitStatements:false
INSERT INTO movie.film_genres (film_id, genre_id)
VALUES ((SELECT id FROM movie.films WHERE title = 'The Shawshank Redemption'),
        (SELECT id FROM movie.genres WHERE name = 'Drama')),
       ((SELECT id FROM movie.films WHERE title = 'The Godfather'),
        (SELECT id FROM movie.genres WHERE name = 'Drama')),
       ((SELECT id FROM movie.films WHERE title = 'The Godfather'),
        (SELECT id FROM movie.genres WHERE name = 'Crime')),
       ((SELECT id FROM movie.films WHERE title = 'Inception'),
        (SELECT id FROM movie.genres WHERE name = 'Drama')),
       ((SELECT id FROM movie.films WHERE title = 'Inception'),
        (SELECT id FROM movie.genres WHERE name = 'Sci-Fi'));