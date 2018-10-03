CREATE TABLE body (
id SERIAL PRIMARY KEY,
name VARCHAR(250)
);

CREATE TABLE engine (
id SERIAL PRIMARY KEY,
name VARCHAR(250)
);

CREATE TABLE gearbox (
id SERIAL PRIMARY KEY,
name VARCHAR(250)
);

CREATE TABLE car (
id serial primary key,
name VARCHAR(250),
body_id INT NOT NULL REFERENCES body(id),
engine_id INT NOT NULL REFERENCES engine(id),
gearbox_id INT NOT NULL REFERENCES gearbox(id)
);

INSERT INTO body(name) 
VALUES ('sedan'), ('hatchback'), ('wagon'), ('coupe');

INSERT INTO engine(name) 
VALUES ('petrol'), ('diesel'), ('electro'), ('dark matter');

INSERT INTO gearbox(name)  
VALUES ('manual6g'), ('manual4g'), ('auto'), ('robot'), ('variator');

INSERT INTO car(name, body_id, engine_id, gearbox_id)
VALUES ('CAR1', 1, 1, 1),
('CAR2', 2, 1, 3),
('CAR3', 1, 2, 3),
('CAR4', 4, 1, 5),
('CAR5', 2, 1, 1),
('CAR6', 1, 1, 5),
('CAR7', 4, 3, 5);


--1. Вывести список всех машин и все привязанные к ним детали.	
SELECT c.name, b.name, e.name, g.name FROM car AS c
INNER JOIN body AS b ON c.body_id = b.id
INNER JOIN engine AS e ON c.engine_id = e.id
INNER JOIN gearbox AS g ON c.gearbox_id = g.id;

--2. Вывести отдельно детали, которые не используются в машине, кузова, двигатели, коробки передач.

SELECT b.name FROM body AS b
LEFT OUTER JOIN car AS c
ON b.id = c.body_id WHERE c.name IS NULL;

SELECT e.name FROM engine AS e
LEFT OUTER JOIN car AS c
ON e.id = c.engine_id WHERE c.name IS NULL;

SELECT g.name FROM gearbox AS g
LEFT OUTER JOIN car AS c
ON g.id = c.gearbox_id WHERE c.name IS NULL;