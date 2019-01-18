CREATE TABLE IF NOT EXISTS todolist (
                                      id SERIAL NOT NULL,
                                      description TEXT,
                                      create_date TIMESTAMP,
                                      done BOOLEAN NOT NULL,
                                      PRIMARY KEY (id)
);

