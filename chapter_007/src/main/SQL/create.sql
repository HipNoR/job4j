CREATE DATABASE Tracker;

CREATE TABLE rights (
right_id serial PRIMARY KEY,
right_name VARCHAR(50)
);

CREATE TABLE roles (
role_id serial PRIMARY KEY,
role_name VARCHAR(50)
);

CREATE TABLE role_right (
right_id INT NOT NULL, 
CONSTRAINT rights_right_id_fk
FOREIGN KEY (right_id)
REFERENCES rights (right_id),
role_id INT NOT NULL,
CONSTRAINT roles_role_id_fk
FOREIGN KEY (role_id)
REFERENCES roles (role_id)
);

CREATE TABLE users (
user_id serial primary key,
role_id INT NOT NULL,
CONSTRAINT roles_role_id_fk
FOREIGN KEY (role_id)
REFERENCES roles (role_id),
user_name VARCHAR(50)
);

CREATE TABLE states (
state_id serial PRIMARY KEY,
state_name VARCHAR(50)
);

CREATE TABLE categories (
category_id serial PRIMARY KEY,
category_name VARCHAR(50)
);

CREATE TABLE items (
item_id serial primary key,
user_id INT NOT NULL, 
CONSTRAINT users_user_id_fk
FOREIGN KEY (user_id)
REFERENCES users (user_id),
state_id INT NOT NULL,
CONSTRAINT states_state_id_fk
FOREIGN KEY (state_id)
REFERENCES states (state_id),
category_id INT NOT NULL,
CONSTRAINT categories_category_id_fk
FOREIGN KEY (category_id)
REFERENCES categories (category_id),
item_name VARCHAR(250) NOT NULL
);

CREATE TABLE comments (
comment_id serial PRIMARY KEY,
item_id INT NOT NULL,
CONSTRAINT items_item_id_fk
FOREIGN KEY (item_id)
REFERENCES items (item_id),
comment_text text
);

CREATE TABLE attaches (
attach_id serial PRIMARY KEY,
item_id INT NOT NULL,
CONSTRAINT items_item_id_fk
FOREIGN KEY (item_id)
REFERENCES items (item_id),
file_name VARCHAR(250)
);


INSERT INTO rights (right_name) 
VALUES
('Full access'),
('Read only');

INSERT INTO roles (role_name)
VALUES
('Admin'),
('Guest');

INSERT INTO role_right (right_id, role_id)
VALUES
((SELECT right_id FROM rights WHERE right_name = 'Full access'), (SELECT role_id FROM roles WHERE role_name = 'Admin')),
((SELECT right_id FROM rights WHERE right_name = 'Read only'), (SELECT role_id FROM roles WHERE role_name = 'Guest'));


INSERT INTO users (role_id, user_name)
VALUES
((SELECT role_id FROM roles WHERE role_name = 'Admin'),'Roman'),
((SELECT role_id FROM roles WHERE role_name = 'Guest'),'Sergey');


INSERT INTO states (state_name)
VALUES
('New'),
('In the work'),
('Closed');

INSERT INTO categories (category_name)
VALUES
('Bug report'),
('Critical issue'),
('Technical problem');

INSERT INTO items (user_id, state_id, category_id, item_name)
VALUES 
((SELECT user_id FROM users WHERE user_name = 'Roman'), (SELECT state_id FROM states WHERE state_name = 'New'),
	(SELECT category_id FROM categories WHERE category_name = 'Bug report'), 'Bug #1'),
((SELECT user_id FROM users WHERE user_name = 'Roman'), (SELECT state_id FROM states WHERE state_name = 'New'), 
	(SELECT category_id FROM categories WHERE category_name = 'Technical problem'), 'Mouse don''t work');


INSERT INTO comments (item_id, comment_text)
VALUES ((SELECT item_id FROM items WHERE item_id = 1), 'При переходе по ссылке вылетает ошибка 404'),
((SELECT item_id FROM items WHERE item_id = 2), 'Я ничего не делал, оно само');

INSERT INTO attaches (item_id, file_name)
VALUES ((SELECT item_id FROM items WHERE item_id = 1), 'скриншот.жыпег');



