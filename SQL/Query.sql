CREATE TABLE animals
(
    id     SERIAL PRIMARY KEY,
    "name" VARCHAR(255),
    area   TEXT
);

INSERT INTO animals(name, area)
VALUES ('Гризли', 'США');

SELECT *
FROM animals;
UPDATE animals
SET name='Grizzly';
SELECT *
FROM animals;

DELETE
FROM animals;
SELECT *
FROM animals;

