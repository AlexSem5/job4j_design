---------------------
CREATE TABLE easy_drink
(
    drink_name VARCHAR(16)   DEFAULT NULL,
    main       VARCHAR(20)   DEFAULT NULL,
    amount1    DECIMAL(3, 1) DEFAULT NULL,
    second     VARCHAR(20)   DEFAULT NULL,
    amount2    DECIMAL(4, 2) DEFAULT NULL,
    directions VARCHAR(250)  DEFAULT NULL
);

INSERT INTO easy_drink (drink_name, main, amount1, second, amount2, directions)
VALUES ('Kiss on the Lips', 'cherry juice', 2.0, 'apricot nectar', 7.00, 'serve over ice with straw'),
       ('Kiss on the Lips', 'cherry juice', 2.0, 'apricot nectar', 7.00, 'serve over ice with straw');
INSERT INTO easy_drink (drink_name, main, amount1, second, amount2, directions)
VALUES ('Hot Gold', 'peach nectar', 3.0, 'orange juice', 6.00, 'pour hot orange juice in mug and add peach nectar');
INSERT INTO easy_drink (drink_name, main, amount1, second, amount2, directions)
VALUES ('Lone Tree', 'soda', 1.5, 'cherry juice', 0.75, 'stir with ice, strain into cocktail glass');
INSERT INTO easy_drink (drink_name, main, amount1, second, amount2, directions)
VALUES ('Greyhound', 'soda', 1.5, 'grapefruit juice', 5.00, 'serve over ice, stir well');
INSERT INTO easy_drink (drink_name, main, amount1, second, amount2, directions)
VALUES ('Indian Summer', 'apple juice', 2.0, 'hot tea', 6.00, 'add juice to mug and top off with hot tea');
INSERT INTO easy_drink (drink_name, main, amount1, second, amount2, directions)
VALUES ('Bull Frog', 'iced tea', 1.5, 'lemonade', 5.00, 'serve over ice with lime slice');
INSERT INTO easy_drink (drink_name, main, amount1, second, amount2, directions)
VALUES ('Soda and It', 'soda', 2.0, 'grape juice', 1.00, 'shake in cocktail glass, no ice');
INSERT INTO easy_drink (drink_name, main, amount1, second, amount2, directions)
VALUES ('Blackthorn', 'tonic water', 1.5, 'pineapple juice', 1.00,
        'stir with ice, strain into cocktail glass with lemon twist');
INSERT INTO easy_drink (drink_name, main, amount1, second, amount2, directions)
VALUES ('Blue Moon', 'soda', 1.5, 'blueberry juice', 0.75,
        'stir with ice, strain into cocktail glass with lemon twist');
INSERT INTO easy_drink (drink_name, main, amount1, second, amount2, directions)
VALUES ('Oh My Gosh', 'peach nectar', 1.0, 'pineapple juice', 1.00, 'stir with ice, strain into shot glass');
INSERT INTO easy_drink (drink_name, main, amount1, second, amount2, directions)
VALUES ('Lime Fizz', 'Sprite', 1.5, 'lime juice', 0.75, 'stir with ice, strain into cocktail glass');

SELECT *
FROM easy_drink;


DELETE
FROM easy_drink
WHERE drink_name ='Indian summer';



SELECT drink_name, directions, amount1
FROM easy_drink
WHERE not drink_name LIKE '%Gold';

DELETE
FROM easy_drink
WHERE drink_name LIKE 'Kiss%';

SELECT *
FROM easy_drink
WHERE amount1 IN (1.5,3);

SELECT drink_name
FROM easy_drink
WHERE drink_name >= 'B'
  AND drink_name < 'C';





