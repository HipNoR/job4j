
--1. Написать запрос получение всех продуктов с типом "СЫР"
SELECT * FROM product AS p
  INNER JOIN type AS t
    ON p.type_id = t.id
WHERE t.name = 'СЫР';

--2. Написать запрос получения всех продуктов, у кого в имени есть слово "мороженное"
SELECT * FROM product
WHERE name LIKE '%Мороженое%';

--3. Написать запрос, который выводит все продукты, срок годности которых заканчивается в следующем месяце.
SELECT * FROM product
WHERE (SELECT date_part('month', expired_date)) = (SELECT date_part('month', CURRENT_DATE + INTERVAL'1 month'));

--4. Написать запрос, который выводит самый дорогой продукт.
SELECT name, MAX(price) FROM product
GROUP BY name
ORDER BY MAX(PRICE)DESC
LIMIT 1;

--5. Написать запрос, который выводит количество всех продуктов определенного типа.
SELECT t.name, count(type_id) FROM product as p
  INNER JOIN type as t
    ON p.type_id = t.id
GROUP BY t.name;

--6. Написать запрос получение всех продуктов с типом "СЫР" и "МОЛОКО"
SELECT * FROM product AS p
  INNER JOIN type AS t
    ON p.type_id = t.id
WHERE
  t.name = 'СЫР'
  OR
  t.name = 'МОЛОКО';

--7. Написать запрос, который выводит тип продуктов, которых осталось меньше 10 штук. 
SELECT t.name, count(type_id) AS value FROM product as p
  INNER JOIN type as t
    ON p.type_id = t.id
GROUP BY t.name
HAVING count(type_id) < 10;

--8. Вывести все продукты и их тип.
SELECT p.name, t.name FROM
  product AS p
  INNER JOIN
  type AS t
    ON p.type_id = t.id;
