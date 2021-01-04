DELETE FROM user_roles;
DELETE FROM dishes;
DELETE FROM restaurants;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password'),
       ('Admin1', 'admin1@gmail.com', 'admin1'),
       ('Admin2', 'admin2@gmail.com', 'admin2');

INSERT INTO user_roles (role, user_id)
VALUES ('USER', 100000),
       ('RESTADMIN', 100001),
       ('RESTADMIN', 100002);

INSERT INTO restaurants (name, rating, user_id)
VALUES ('астория', 1, 100001),
       ('бавария', 2, 100001),
       ('венгрия', 3, 100001),
       ('голландия', 4, 100002),
       ('дрезден', 5, 100002),
       ('ереван', 6, 100002);

INSERT INTO dishes (name, price, rest_id)
VALUES ('блюдо 1', 1000, (SELECT id FROM restaurants WHERE name='астория' limit 1)),
       ('блюдо 2', 2000, (SELECT id FROM restaurants WHERE name='астория' limit 1));

