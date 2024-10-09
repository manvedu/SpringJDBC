CREATE TABLE IF NOT EXISTS Users (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    surname VARCHAR(255) NOT NULL,
    birthdate DATE NOT NULL
);

CREATE TABLE IF NOT EXISTS Friendships (
    id BIGSERIAL PRIMARY KEY,
    userid1 BIGINT NOT NULL,
    userid2 BIGINT NOT NULL,
    timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (userid1) REFERENCES Users(id),
    FOREIGN KEY (userid2) REFERENCES Users(id)
);

CREATE TABLE IF NOT EXISTS Posts (
    id BIGSERIAL PRIMARY KEY,
    userId BIGINT NOT NULL,
    text VARCHAR(500) NOT NULL,
    timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (userId) REFERENCES Users(id)
);

CREATE TABLE IF NOT EXISTS Likes (
    id BIGSERIAL PRIMARY KEY,
    postid BIGINT NOT NULL,
    userid BIGINT NOT NULL,
    timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (postid) REFERENCES Posts(id),
    FOREIGN KEY (userid) REFERENCES Users(id)
);