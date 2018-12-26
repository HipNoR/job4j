CREATE TABLE IF NOT EXISTS vacancy (
                                     id serial NOT NULL,
                                     date DATE,
                                     title VARCHAR(250),
                                     url text,
                                     descript text
);

CREATE TABLE IF NOT EXISTS last_start_date (
                                             id INT,
                                             date DATE
);

INSERT INTO last_start_date (id)
SELECT '1' WHERE '1' NOT IN (
  SELECT id FROM last_start_date
);