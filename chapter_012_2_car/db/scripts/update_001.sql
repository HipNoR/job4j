CREATE TABLE IF NOT EXISTS engines (
                                     engine_id SERIAL PRIMARY KEY,
                                     name VARCHAR(50) UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS gearboxes (
                                       gearbox_id SERIAL PRIMARY KEY,
                                       name VARCHAR(50) UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS bodies (
                                    body_id SERIAL PRIMARY KEY,
                                    name VARCHAR(50) UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS colours (
                                     colour_id SERIAL PRIMARY KEY,
                                     name VARCHAR(50) UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS brands (
                                    brand_id SERIAL PRIMARY KEY,
                                    name VARCHAR(50) UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS models (
                                    model_id SERIAL PRIMARY KEY,
                                    name VARCHAR(50) UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS branded_model (
                                           bm_id SERIAL PRIMARY KEY,
                                           brand_id INTEGER NOT NULL REFERENCES brands(brand_id),
                                           model_id INTEGER NOT NULL REFERENCES models(model_id),
                                           UNIQUE (brand_id, model_id)
);

CREATE TABLE IF NOT EXISTS persons (
                                     person_id SERIAL PRIMARY KEY,
                                     login VARCHAR(50) UNIQUE NOT NULL,
                                     password VARCHAR(50) NOT NULL,
                                     name VARCHAR(50) NOT NULL,
                                     email VARCHAR(50),
                                     phone INTEGER
);

CREATE TABLE IF NOT EXISTS cars (
                                  car_id SERIAL,
                                  person_id INTEGER NOT NULL REFERENCES persons(person_id),
                                  description VARCHAR(255),
                                  bm_id INTEGER NOT NULL REFERENCES branded_model(bm_id),
                                  engine_id INTEGER NOT NULL REFERENCES engines(engine_id),
                                  gearbox_id INTEGER NOT NULL REFERENCES gearboxes(gearbox_id),
                                  body_id INTEGER NOT NULL REFERENCES bodies(body_id),
                                  colour_id INTEGER NOT NULL REFERENCES colours(colour_id),
                                  PRIMARY KEY (car_id)
);



INSERT INTO engines (name) VALUES
('diesel'), ('petrol'), ('electro'), ('hybrid');

INSERT INTO gearboxes (name) VALUES
('manual'), ('auto'), ('cvt'), ('robot');

INSERT INTO bodies (name) VALUES
('sedan'), ('hatchback 3d'), ('hatchback 5d'), ('wagon');

INSERT INTO colours (name) VALUES
('silver'), ('black'), ('blue'), ('red'), ('yellow');

INSERT INTO brands (name) VALUES
('ford'), ('audi'), ('toyota'), ('vaz');

INSERT INTO models (name) VALUES
('focus'), ('a8'), ('crown'), ('2106'), ('transit'), ('tt'), ('avensis'), ('2109');

INSERT INTO branded_model (brand_id, model_id) VALUES
((SELECT brand_id FROM brands WHERE name = 'ford'), (SELECT model_id FROM models WHERE name='focus')),
((SELECT brand_id FROM brands WHERE name = 'ford'), (SELECT model_id FROM models WHERE name='transit')),
((SELECT brand_id FROM brands WHERE name = 'audi'), (SELECT model_id FROM models WHERE name='a8')),
((SELECT brand_id FROM brands WHERE name = 'audi'), (SELECT model_id FROM models WHERE name='tt')),
((SELECT brand_id FROM brands WHERE name = 'toyota'), (SELECT model_id FROM models WHERE name='crown')),
((SELECT brand_id FROM brands WHERE name = 'toyota'), (SELECT model_id FROM models WHERE name='avensis')),
((SELECT brand_id FROM brands WHERE name = 'vaz'), (SELECT model_id FROM models WHERE name='2106')),
((SELECT brand_id FROM brands WHERE name = 'vaz'), (SELECT model_id FROM models WHERE name='2109'));