CREATE TABLE IF NOT EXISTS tasks (
                                   id SERIAL PRIMARY KEY,
                                   name VARCHAR(250),
                                   description VARCHAR(500),
                                   create_date TIMESTAMP
);

CREATE TABLE IF NOT EXISTS comments (
                                      id serial PRIMARY KEY,
                                      task_id INT NOT NULL REFERENCES tasks(id),
                                      comment text
);
