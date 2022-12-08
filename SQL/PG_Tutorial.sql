/*Inner join*/
SELECT c.customer_id, c.first_name, c.last_name, p.amount, p.payment_date
FROM customer c
INNER JOIN payment p on c.customer_id = p.customer_id
WHERE c.customer_id = 5
ORDER BY payment_date;

SELECT
    c.customer_id,
    c.first_name customer_first_name,
    c.last_name customer_last_name,
    s.first_name staff_first_name,
    s.last_name staff_last_name,
    p.amount,
    p.payment_date
FROM
    customer c
        INNER JOIN payment p
                   ON p.customer_id = c.customer_id
        INNER JOIN staff s
                   ON p.staff_id = s.staff_id
ORDER BY payment_date;

/*subquery*/
SELECT film_id,title,rental_rate
FROM film
WHERE rental_rate > (SELECT AVG(rental_rate)
                     FROM film);

/*
Inner join example
*/
CREATE TABLE customer1
(
    customer_id SERIAL PRIMARY KEY,
    name  VARCHAR(30)
);

CREATE TABLE payment1
(
    payment_id SERIAL PRIMARY KEY,
    amount    INT NOT NULL,
    customer_id int,
        FOREIGN KEY (customer_id)
            REFERENCES customer1 (customer_id)
);

INSERT INTO customer1(name)
VALUES
('tom'), ('brad'), ('sam');

INSERT INTO payment1 (amount, customer_id)
VALUES
(5000, 1), (3000, 3), (5000,1), (7000, 2), (50000, 1);

SELECT C.customer_id,c.name,p.payment_id, p.amount
FROM payment1 p INNER JOIN customer1 C ON p.customer_id = C.customer_id;

SELECT
    f.film_id,
    title,
    inventory_id
FROM
    film f
        LEFT OUTER JOIN inventory i
                  ON i.film_id = f.film_id
order by title;

