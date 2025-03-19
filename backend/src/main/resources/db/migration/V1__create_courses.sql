CREATE TABLE courses (
    id SERIAL PRIMARY KEY,
    university VARCHAR(255) NOT NULL,
    name VARCHAR(255) NOT NULL,
    duration INT NOT NULL,
    area VARCHAR(255),
    disciplines TEXT[]
);
