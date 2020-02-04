--2.1
SELECT *
FROM employee;

SELECT *
FROM employee
WHERE lastname = 'King';

SELECT *
FROM employee
WHERE firstname = 'Andrew' AND reportsto IS NULL;

--2.2

SELECT *
FROM album
ORDER BY title
DESC;

SELECT *
FROM customer
ORDER BY city
ASC;

--2.3
INSERT INTO genre
VALUES (26, 'EDM');

INSERT INTO genre
VALUES (27, 'Street');

INSERT INTO employee
VALUES (10, 'anthony', 'devoz');

INSERT INTO employee
VALUES (11, 'danny', 'devoz');

INSERT INTO customer
VALUES (100, 'batman', 'rules', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'anthonydevoz@yahoo.com');

INSERT INTO customer
VALUES (124, 'superman', 'sucks', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'anthonydevoz@yahoo.com');

--2.4 UPDATE

UPDATE customer
SET firstname = 'Robert', lastname = 'Mitchell'
WHERE firstname = 'Aaron' AND lastname = 'Mitchell';

UPDATE artist 
SET name = 'CCR'
WHERE name = 'Creedence Clearwater Revival';

SELECT *
FROM invoice
WHERE billingaddress 
LIKE 'T%';

--2.6 BETWEEN
SELECT *
FROM invoice
WHERE total
BETWEEN 15 AND 50;

SELECT *
FROM employee
WHERE hiredate
BETWEEN '2003-06-01' AND '2004-03-01';

DELETE
FROM customer
WHERE firstname = 'Robert' AND lastname = 'Walter';

--3 FUNCTIONS
