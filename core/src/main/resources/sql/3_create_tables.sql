\connect newsmanagement
CREATE TABLE tag
(
    id   BIGSERIAL PRIMARY KEY,
    name VARCHAR(15) UNIQUE NOT NULL
);

\connect newsmanagement
CREATE TABLE author
(
    id   BIGSERIAL PRIMARY KEY,
    name VARCHAR(15) UNIQUE NOT NULL
);

\connect newsmanagement
CREATE TABLE news
(
    id   BIGSERIAL PRIMARY KEY,
    title VARCHAR(30) UNIQUE NOT NULL,
    content VARCHAR(255) NOT NULL,
    author_id BIGINT,
    created timestamp,
    modified timestamp,

    CONSTRAINT fk_news_author FOREIGN KEY (author_id) REFERENCES author (id)
);

