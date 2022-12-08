CREATE TABLE clown_info
(
    name       VARCHAR(50) DEFAULT NULL,
    last_seen  VARCHAR(50) DEFAULT NULL,
    appearance VARCHAR(50) DEFAULT NULL,
    activities VARCHAR(50) DEFAULT NULL
);

INSERT INTO clown_info
VALUES ('Elsie', 'Cherry Hill Senior Center', 'F, red hair, green dress, huge feet', 'balloons, little car');

INSERT INTO clown_info
VALUES ('Pickles', 'Jack Green''s party', 'M, orange hair, blue suit, huge feet', 'mime');

INSERT INTO clown_info
VALUES ('Snuggles', 'BallMart', 'F, yellow shirt, baggy red pants', 'horn, umbrella');

INSERT INTO clown_info
VALUES ('Mr. Hobo', 'Party for Eric Gray', 'M, cigar, black hair, tiny hat violin');

INSERT INTO clown_info
VALUES ('Clarabelle', 'Belmont Senior Center', 'F, pink hair, huge flower, blue dress', 'yelling, dancing');

INSERT INTO clown_info
VALUES ('Scooter', 'Oakland Hospital', 'M, blue hair, red suit, huge nose', 'balloons');

INSERT INTO clown_info
VALUES ('Zippo', 'Millstone Mall', 'F, orange suit, baggy pants', 'dancing');

INSERT INTO clown_info
VALUES ('Babe', 'Earls Autos', 'F, all pink and sparkly', 'balancing, little car');

INSERT INTO clown_info
VALUES ('Bonzo', '', 'M, in drag, polka dotted dress', 'singing, dancing');

INSERT INTO clown_info
VALUES ('Sniffles', 'Tracy''s', 'M, green and purple suit, pointy nose', '');

INSERT INTO clown_info
VALUES ('Mr. Hobo', 'BG', 'M, cigar, black hair, tiny hat violin'),
       ('Mr. Hobo', 'Tracy''s', 'M, cigar, black hair, tiny hat violin');

UPDATE clown_info
SET appearance = 'F, yellow shirt, baggy blue pants'
WHERE name ='Snuggles';

UPDATE clown_info
SET last_seen ='Dickson Park'
WHERE name ='Bonzo';

UPDATE clown_info
set activities ='climbing into tiny car'
WHERE name = 'Sniffles';


SELECT *
FROM clown_info
WHERE name = 'Mr. Hobo';

DELETE
FROM clown_info
WHERE name = 'Mr. Hobo';

SELECT *
FROM clown_info
WHERE name = 'Mr. Hobo'
  AND last_seen <> 'Tracy''s';

INSERT INTO clown_info
VALUES ('Mr. Hobo', 'Party for Eric Gray', 'M, cigar, black hair, tiny hat', 'violin'),
       ('Mr. Hobo', 'BG', 'M, cigar, black hair, tiny hat', 'violin'),
       ('Mr. Hobo', 'Tracy''s', 'M, cigar, black hair, tiny hat', 'violin');

DELETE
FROM clown_info
WHERE name = 'Mr. Hobo'
  AND last_seen <> 'Tracy''s';

