CREATE TABLE movie_table
(
    movie_id  SERIAL PRIMARY KEY,
    title     VARCHAR(50) NOT NULL,
    rating    VARCHAR(5)  NOT NULL,
    category  VARCHAR(10) NOT NULL,
    purchased DATE        NOT NULL

);

INSERT INTO movie_table (title, rating, category, purchased)
VALUES ('Family', 'G', 'family', '2002-03-06');
INSERT INTO movie_table ( title, rating, category, purchased)
VALUES ('Shiny Things, The', 'PG', 'drama', '2002-03-06');
INSERT INTO movie_table ( title, rating, category, purchased)
VALUES ('End of the Line', 'R', 'misc', '2001-02-05');
INSERT INTO movie_table ( title, rating, category, purchased)
VALUES ( 'A Rat named Darcy', 'G', 'family', '2003-04-19');
INSERT INTO movie_table ( title, rating, category, purchased)
VALUES ( 'Head First Rules', 'R', 'action', '2003-04-19');
INSERT INTO movie_table ( title, rating, category, purchased)
VALUES ( 'Mad Clowns', 'R', 'horror', '1999-11-20');
INSERT INTO movie_table ( title, rating, category, purchased)
VALUES ( 'Greg: The Untold Story', 'PG', 'action', '2001-02-05');
INSERT INTO movie_table ( title, rating, category, purchased)
VALUES ( 'Potentially Habitable Planet', 'PG', 'scifi', '2001-02-05');
INSERT INTO movie_table ( title, rating, category, purchased)
VALUES ( 'Weird Things from Space', 'PG', 'scifi', '2003-04-19');
INSERT INTO movie_table ( title, rating, category, purchased)
VALUES ( 'Shark Bait', 'G', 'misc', '1999-11-20');
INSERT INTO movie_table ( title, rating, category, purchased)
VALUES ( 'Take it Back', 'R', 'comedy', '2001-02-05');

SELECT title, category, purchased
FROM movie_table
ORDER BY category DESC , purchased;





drop TABLE movie_table ;


UPDATE movie_table
SET category =
        CASE
            WHEN title = 'Shiny Things, The' AND rating = 'PG' THEN 'not family'
            WHEN title ='Family' THEN 'family'
            END;

UPDATE movie_table
SET title ='Shiny Things'
WHERE movie_id =1;

SELECT movie_id, category,
       CASE
           WHEN movie_id =1 THEN 'first'
           WHEN movie_id =1 THEN 'second'
           ELSE 'others'
END length
FROM movie_table
ORDER BY movie_id;

ALTER TABLE movie_table
    ADD COLUMN id SERIAL PRIMARY KEY;

