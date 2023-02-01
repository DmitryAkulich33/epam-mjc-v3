\connect newsmanagement
INSERT INTO tag(name)
values ('sport'),
       ('weather'),
       ('shop'),
       ('culture');

\connect newsmanagement
INSERT INTO author(name)
values ('Jim'),
       ('John'),
       ('David'),
       ('Daniel');

\connect newsmanagement
INSERT INTO news(title, content, author_id, created, modified)
values ('Australian Open 2023 run', 'Australian Open 2023: Aryna Sabalenka beats Elena Rybakina to win Melbourne title', 1, '2022-03-13 15:55:52.611265', '2022-03-13 15:55:52.611265'),
       ('Conor McGregor', 'Conor McGregor: UFC star says he got away with his life after being knocked off his bike by a car', 1, '2022-03-14 15:55:52.611265', '2022-03-14 15:55:52.611265'),
       ('Saturday transfer gossip', 'Saturday transfer gossip: Mourinho, Berge, Elanga, Caicedo, Bellingham, Gusto, Martinelli', 2, '2022-03-15 15:55:52.611265', '2022-03-15 15:55:52.611265'),
       ('Morocco World Cup run', 'Morocco World Cup run - and the Englishman who helped plot it', 3, '2022-03-16 15:55:52.611265', '2022-03-16 15:55:52.611265'),
       ('Christian Streich', 'Christian Streich: The skinny-dipping managerial maverick shaking up the Bundesliga', 3, '2022-03-17 15:55:52.611265', '2022-03-17 15:55:52.611265');

\connect newsmanagement
INSERT INTO news_tag(news_id, tag_id)
values (1, 1),
       (1, 4),
       (2, 1),
       (3, 1),
       (3, 3),
       (4, 1),
       (4, 4),
       (5, 1),
       (5, 3);

\connect newsmanagement
INSERT INTO comment(content, news_id, created, modified)
values ('Good news!', 1, '2022-04-13 15:55:52.611265', '2022-04-13 15:55:52.611265'),
       ('Brilliant', 1, '2022-04-14 15:55:52.611265', '2022-04-14 15:55:52.611265'),
       ('It is not interesting for me', 2, '2022-04-15 15:55:52.611265', '2022-04-15 15:55:52.611265'),
       ('Good luck Conor', 2, '2022-04-16 15:55:52.611265', '2022-04-16 15:55:52.611265'),
       ('Good day', 3, '2022-04-17 15:55:52.611265', '2022-04-17 15:55:52.611265'),
       ('Bad day', 4, '2022-04-17 15:55:52.611265', '2022-04-18 15:55:52.611265'),
       ('Super!!!', 5, '2022-04-17 15:55:52.611265', '2022-04-19 15:55:52.611265');

