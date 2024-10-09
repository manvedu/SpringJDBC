CREATE TABLE IF NOT EXISTS Directors (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    age INT
);

CREATE TABLE IF NOT EXISTS Movies (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    genre VARCHAR(100),
    release_year INT,
    director_id INT
);

CREATE TABLE IF NOT EXISTS Reviews (
    id SERIAL PRIMARY KEY,
    movie_id INT,
    rating INT CHECK (rating BETWEEN 1 AND 5),
    comment TEXT
);