DELETE FROM user_roles;
DELETE FROM dishes;
DELETE FROM restaurants;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

-- $2a$10$gyFljJObLkpF9F0b4mKJiegFxPevbhm0Am9.8gwG0XtkmAf/rPduu = password

INSERT INTO users (name, email, password)
VALUES ('user', 'user@yandex.ru', '$2a$10$gyFljJObLkpF9F0b4mKJiegFxPevbhm0Am9.8gwG0XtkmAf/rPduu'),
       ('owner1', 'owner1@gmail.com', '$2a$10$gyFljJObLkpF9F0b4mKJiegFxPevbhm0Am9.8gwG0XtkmAf/rPduu'),
       ('owner2', 'owner2@gmail.com', '$2a$10$gyFljJObLkpF9F0b4mKJiegFxPevbhm0Am9.8gwG0XtkmAf/rPduu'),
       ('admin', 'admin@gmail.com', '$2a$10$gyFljJObLkpF9F0b4mKJiegFxPevbhm0Am9.8gwG0XtkmAf/rPduu');

INSERT INTO user_roles (role, user_id)
VALUES ('USER', 100000),
       ('OWNER', 100001),
       ('OWNER', 100002),
       ('ADMIN', 100003);

INSERT INTO restaurants (name, rating, user_id)
VALUES ('астория', 1, 100001),
       ('бавария', 2, 100001),
       ('венгрия', 3, 100001),
       ('голландия', 4, 100002),
       ('дрезден', 5, 100002),
       ('ереван', 6, 100002);

INSERT INTO dishes (name, price, restaurant_id, modify)
VALUES ('блюдо1 из астории', 1000, (SELECT id FROM restaurants WHERE name = 'астория' limit 1), now()),
       ('блюдо2 из астории', 1200, (SELECT id FROM restaurants WHERE name = 'астория' limit 1), now()),
       ('блюдо1 из баварии', 1000, (SELECT id FROM restaurants WHERE name = 'бавария' limit 1), now()),
       ('блюдо2 из баварии', 1200, (SELECT id FROM restaurants WHERE name = 'бавария' limit 1), now()),
       ('блюдо1 из венгрии', 1000, (SELECT id FROM restaurants WHERE name = 'венгрия' limit 1), now()),
       ('блюдо2 из венгрии', 1200, (SELECT id FROM restaurants WHERE name = 'венгрия' limit 1), now()),
       ('блюдо1 из голландии', 1000, (SELECT id FROM restaurants WHERE name = 'голландия' limit 1), now()),
       ('блюдо2 из голландии', 1200, (SELECT id FROM restaurants WHERE name = 'голландия' limit 1), now()),
       ('блюдо1 из дрездена', 1000, (SELECT id FROM restaurants WHERE name = 'дрезден' limit 1), now() - interval '1 day'),
       ('блюдо2 из дрездена', 1200, (SELECT id FROM restaurants WHERE name = 'дрезден' limit 1), now()),
       ('блюдо1 из еревана', 1000, (SELECT id FROM restaurants WHERE name = 'ереван' limit 1), now() - interval '1 day'),
       ('блюдо2 из еревана', 1200, (SELECT id FROM restaurants WHERE name = 'ереван' limit 1), now() - interval '1 day');


