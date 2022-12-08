CREATE TABLE my_contacts
(
    contact_id SERIAL PRIMARY KEY,
    last_name  VARCHAR(30),
    first_name VARCHAR(20),
    email      VARCHAR(50),
    gender     CHAR(1),
    birthday   DATE,
    profession VARCHAR(50),
    city       VARCHAR(50),
    state      VARCHAR(50),
    status     VARCHAR(20),
    interest1  VARCHAR(100),
    interest2  VARCHAR(100),
    interest3  VARCHAR(100),
    interest4  VARCHAR(100),
    seeking    VARCHAR(100)
);

INSERT INTO my_contacts (last_name, first_name, email, gender, birthday, profession, city, state, status, interest1,
                         interest2, interest3, interest4,
                         seeking)
VALUES ('Anderson', 'Jillian', 'jill_anderson@nbreakneckpizza.com', 'F', '1980-09-05', 'Technical Writer',
        'Palo Alto', ' CA', 'single', 'hiking', 'pets', 'travel', 'movies', 'relationship, friends');
INSERT INTO my_contacts (last_name, first_name, email, gender, birthday, profession, city, state, status, interest1,
                         interest2, interest3, interest4,
                         seeking)
VALUES ('Kenton', 'Leo', 'lkenton@starbuzzcoffee.com', 'M', '1974-01-10', 'Manager', 'San Francisco', ' CA', 'divorced',
        'horses', 'music', 'books', 'boating', 'women to date');
INSERT INTO my_contacts (last_name, first_name, email, gender, birthday, profession, city, state, status, interest1,
                         interest2, interest3, interest4,
                         seeking)
VALUES ('McGavin', 'Darrin', 'captainlove@headfirsttheater.com', 'M', '1966-01-23', 'Cruise Ship Captain',
        'San Diego', ' CA', 'single', 'dogs', 'pets', 'sports', 'movies',
        'women for casual relationships');
INSERT INTO my_contacts (last_name, first_name, email, gender, birthday, profession, city, state, status, interest1,
                         interest2, interest3, interest4,
                         seeking)
VALUES ('Franklin', 'Joe', 'joe_franklin@leapinlimos.com', 'M', '1977-04-28', 'Software Sales', 'Dallas', ' TX',
        'married',
        'horses', 'pets', 'fishing', 'movies'
           , 'new job');
INSERT INTO my_contacts (last_name, first_name, email, gender, birthday, profession, city, state, status, interest1,
                         interest2, interest3, interest4,
                         seeking)
VALUES ('Hamilton', 'Jamie', 'dontbother@starbuzzcoffee.com', 'F', '1964-09-10', 'System Administrator',
        'Princeton', ' NJ', 'married', 'hiking', 'boating', 'travel', 'sports', 'nothing');
INSERT INTO my_contacts (last_name, first_name, email, gender, birthday, profession, city, state, status, interest1,
                         interest2, interest3, interest4,
                         seeking)
VALUES ('Chevrolet', 'Maurice', 'bookman4u@objectville.net', 'M', '1962-07-01', 'Bookshop Owner', 'Mountain View',
        ' CA',
        'married', 'hiking', 'boating', 'travel', 'sports', 'friends');
INSERT INTO my_contacts (last_name, first_name, email, gender, birthday, profession, city, state, status, interest1,
                         interest2, interest3, interest4,
                         seeking)
VALUES ('Kroger', 'Renee', 'poorrenee@mightygumball.net', 'F', '1976-12-03', 'Unemployed', 'San Francisco', ' CA',
        'divorced', 'horses', 'pets', 'hiking', 'movies', 'employment');
INSERT INTO my_contacts (last_name, first_name, email, gender, birthday, profession, city, state, status, interest1,
                         interest2, interest3, interest4,
                         seeking)
VALUES ('Mendoza', 'Angelina', 'angelina@starbuzzcoffee.com', 'F', '1979-08-19', 'UNIX Sysadmin', 'San Francisco', 'CA',
        'married', 'fishing', 'pets', 'books', 'travel', 'new job');


CREATE TABLE profession (profession1)
AS
SELECT profession AS mc_prof
FROM my_contacts
GROUP BY Mc_Prof
ORDER BY Mc_Prof;
ALTER TABLE profession
    ADD COLUMN id SERIAL PRIMARY KEY;

/*CREATE TABLE profession2
(
    id         SERIAL PRIMARY KEY,
    profession VARCHAR(70)
);
INSERT INTO profession2(profession)
SELECT profession AS mc_prof
FROM my_contacts
GROUP BY Mc_Prof
ORDER BY Mc_Prof;*/

SELECT mc.last_name, mc.first_name, p.profession1
FROM my_contacts AS mc
         INNER JOIN
     profession AS p
     ON mc.contact_id = p.id;














UPDATE my_contacts
SET profession = 'Manager'
WHERE contact_id = 7;


DROP TABLE my_contacts;
DROP TABLE interests;

ALTER TABLE my_contacts
    ADD COLUMN interest1 VARCHAR(50),
    ADD COLUMN interest2 VARCHAR(50),
    ADD COLUMN interest3 VARCHAR(50),
    ADD COLUMN interest4 VARCHAR(50);

ALTER TABLE my_contacts
    DROP COLUMN interests;

DELETE
FROM my_contacts
WHERE contact_id >= 9
  AND contact_id <= 15;;

INSERT INTO my_contacts (interest1, interest2, interest3, interest4)
VALUES ('hiking', 'pets', 'travel', 'movies'),
       ('horses', 'music', 'books', 'boating'),
       ('dogs', 'pets', 'sports', 'movies'),
       ('horses', 'pets', 'fishing', 'movies'),
       ('hiking', 'boating', 'travel', 'sports'),
       ('horses', 'pets', 'hiking', 'movies'),
       ('fishing', 'pets', 'books', 'travel'),
       ('fishing', 'boating', 'books', 'travel');


CREATE TABLE interests
(
    int_id     SERIAL PRIMARY KEY NOT NULL,
    interest   VARCHAR(50)        NOT NULL,
    contact_id INT                NOT NULL,
    CONSTRAINT my_contacts_contact_id_fk
        FOREIGN KEY (contact_id)
            REFERENCES my_contacts (contact_id)
);


SELECT status
FROM my_contacts
GROUP BY status
ORDER BY status;

UPDATE my_contacts
SET email ='jill_anderson@nbreakneckpizza.com'
WHERE contact_id = 1;

ALTER TABLE my_contacts
    ADD COLUMN phone VARCHAR(10);

ALTER TABLE my_contacts
    ADD COLUMN state CHAR(2),
    ADD COLUMN city  VARCHAR(50);

SELECT "right"(location, 2) state
FROM my_contacts;
SELECT SPLIT_PART(location, ',', 1) city
FROM my_contacts;

UPDATE my_contacts
SET state = "right"(location, 2);

UPDATE my_contacts
SET city = SPLIT_PART(location, ',', 1);

SELECT SPLIT_PART(interests, ',', 1) interest1
FROM my_contacts;


CREATE TABLE project_list
(
    number            INT,
    descriptionofproj VARCHAR(50) DEFAULT NULL,
    contractoronjob   VARCHAR(10) DEFAULT NULL
);

INSERT INTO project_list (number, descriptionofproj, contractoronjob)
VALUES (1, 'outside house painting', 'Murphy');
INSERT INTO project_list (number, descriptionofproj, contractoronjob)
VALUES (2, 'kitchen remodel', 'Valdez');
INSERT INTO project_list (number, descriptionofproj, contractoronjob)
VALUES (3, 'wood floor installation', 'Keller');
INSERT INTO project_list (number, descriptionofproj, contractoronjob)
VALUES (4, 'roofing', 'Jackson');

ALTER TABLE project_list
    ADD COLUMN proj_id SERIAL PRIMARY KEY;

ALTER TABLE project_list
    RENAME contractoronjob TO con_name;

ALTER TABLE project_list
    RENAME descriptionofproj TO proj_desc;

ALTER TABLE project_list
    ALTER COLUMN con_name TYPE VARCHAR(55);

ALTER TABLE project_list
    DROP COLUMN number;


