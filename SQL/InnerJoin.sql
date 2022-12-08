CREATE TABLE contacts
(
    contact_id SERIAL PRIMARY KEY,
    last_name  VARCHAR(30),
    first_name VARCHAR(20),
    prof_id    INT NOT NULL,
    CONSTRAINT contacts_prof_id_fk
        FOREIGN KEY (prof_id)
            REFERENCES new_profession (prof_id)
);

INSERT INTO contacts (last_name, first_name, prof_id)
VALUES ('bob', 'bob', 1),
       ('bob', 'brad', 1),
       ('hob', 'hob', 1),
       ('tob', 'tob', 5),
       ('rob', 'rob', 3),
       ('bbb', 'bbb', 2),
       ('kob', 'kob', 5);

CREATE TABLE new_profession
(
    prof_id    SERIAL PRIMARY KEY,
    profession VARCHAR(55)
);
INSERT INTO new_profession (profession)
VALUES ('manager'),
       ('driver'),
       ('developer'),
       ('salesman'),
       ('courier');

INSERT INTO new_profession (profession)
VALUES ('it-spec'),
       ('physics'),
       ('taxi');

/*one-to-one relationship*/
CREATE TABLE job_current
(
    contact_id int PRIMARY KEY,
    CONSTRAINT job_current_contact_id_fk
        FOREIGN KEY (contact_id)
            REFERENCES contacts (contact_id),
    title      VARCHAR(55),
    salary     INT,
    start_date DATE
);

INSERT INTO job_current (contact_id, title, salary)
VALUES (1, 'Cook', 50000),
       (2, 'Web developer', 90000),
       (3, 'Cook', 70000),
       (4, 'Web developer', 90000),
       (5, 'IT', 90000);

UPDATE job_current
SET salary = 150000
WHERE contact_id = 4;

/*one-to-one relationship*/
CREATE TABLE job_desired
(
    contact_id  int PRIMARY KEY,
    CONSTRAINT job_desired_contact_id_fk
        FOREIGN KEY (contact_id)
            REFERENCES contacts (contact_id),
    title       VARCHAR(55),
    salary_low  INT,
    salary_high INT,
    available   BOOLEAN,
    years_exp   INT
);
INSERT INTO job_desired(contact_id, title, salary_low, salary_high, available, years_exp)
VALUES (1, 'Web developer', 100000, 110000, TRUE, 5),
       (2, 'Web developer', 90000, 110000, TRUE, 6),
       (3, 'Web developer', 95000, 120000, TRUE, 3),
       (4, 'Web developer', 100100, 130000, TRUE, 7),
       (5, 'Web developer', 200000, 110000, TRUE, 11);

CREATE TABLE job_listings
(
    job_id SERIAL PRIMARY KEY,
    title  VARCHAR(55),
    salary INT
);

INSERT INTO job_listings (job_id, title, salary)
VALUES (1, 'Web developer', 105000),
       (2, 'Cook', 75000);

SELECT MAX(salary)
FROM job_current
WHERE title = 'Web developer';

SELECT c.first_name
FROM contacts c
         NATURAL JOIN job_current jc
WHERE jc.salary < (SELECT MAX(salary)
                   FROM job_current
                   WHERE title = 'Web developer');

SELECT AVG(salary) average_salary
FROM job_current
WHERE title = 'Web developer';

SELECT c.first_name, c.last_name, jc.salary
FROM job_current jc
         INNER JOIN contacts C ON C.contact_id = jc.contact_id
WHERE jc.title = 'Web developer';

/*combine previous queries*/
SELECT c.first_name,
       c.last_name,
       jc.salary,
       jc.salary - (SELECT AVG(salary) average_salary
                    FROM job_current
                    WHERE title = 'Web developer') difference
FROM job_current jc
         INNER JOIN contacts C ON C.contact_id = jc.contact_id
WHERE jc.title = 'Web developer';


SELECT jc.salary, c.first_name, c.last_name
FROM job_current jc
         INNER JOIN contacts C ON C.contact_id = jc.contact_id
WHERE jc.salary > (SELECT jc.salary
                   FROM job_current jc
                            INNER JOIN contacts c ON c.contact_id = jc.contact_id
                   WHERE c.first_name = 'rob')
ORDER BY salary DESC;


SELECT c.first_name, c.last_name
FROM job_desired jd
         INNER JOIN contacts C ON jd.contact_id = C.contact_id
WHERE jd.title = 'Web developer'
  AND jd.salary_low <= 105000;

SELECT c.first_name, c.last_name, jc.title
FROM job_current jc
         INNER JOIN contacts C ON C.contact_id = jc.contact_id
WHERE jc.title IN (SELECT title FROM job_listings)
ORDER BY jc.title;

/*three options*/
SELECT first_name, last_name
FROM contacts
WHERE contact_id = (SELECT contact_id
                    FROM job_current
                    WHERE salary = (SELECT MAX(salary) FROM job_current));

SELECT c.first_name, c.last_name, jc.salary
FROM job_current jc
         INNER JOIN contacts C ON C.contact_id = jc.contact_id
WHERE salary = (SELECT MAX(salary) FROM job_current);

SELECT c.first_name, c.last_name, jc.salary
FROM job_current jc
         INNER JOIN contacts C ON C.contact_id = jc.contact_id
ORDER BY Jc.salary DESC
LIMIT 1;


SELECT np.profession, c.first_name
FROM contacts c
         INNER JOIN new_profession Np ON Np.prof_id = c.prof_id
ORDER BY np.profession;

SELECT new_profession.profession, contacts.first_name
FROM new_profession
         NATURAL JOIN contacts;

SELECT c.first_name AS name, np.profession
FROM new_profession AS np
         CROSS JOIN contacts C
ORDER BY c.first_name;

SELECT c.first_name, np.profession
FROM contacts c
         CROSS JOIN new_profession np;

SELECT new_profession.profession, contacts.first_name
FROM new_profession,
     contacts;


CREATE TABLE passport
(
    id     SERIAL PRIMARY KEY,
    series INT,
    number INT
);
CREATE TABLE people
(
    id          SERIAL PRIMARY KEY,
    name        VARCHAR(255),
    passport_id INT REFERENCES passport (id) UNIQUE
);

INSERT INTO passport (series, number)
VALUES (55, 575), (57, 777), (33, 373);

INSERT INTO people (name, passport_id)
VALUES ('tom', 3), ('bob', 2), ('sam', 1);

SELECT P2.name, p.series
FROM passport p
         INNER JOIN people P2 ON p.id = P2.passport_id;

