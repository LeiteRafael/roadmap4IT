CREATE TABLE disciplines (
    id SERIAL PRIMARY KEY,
    code VARCHAR(255) NOT NULL UNIQUE,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    semester INT NOT NULL,
    prerequisites TEXT[],  
    unlocks TEXT[]
);
