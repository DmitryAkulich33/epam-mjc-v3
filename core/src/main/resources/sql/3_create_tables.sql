\connect newsmanagement
CREATE TABLE tag
(
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(15) UNIQUE NOT NULL
);

\connect newsmanagement
CREATE TABLE author
(
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(15) UNIQUE NOT NULL
);

\connect newsmanagement
CREATE TABLE news
(
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(30) UNIQUE NOT NULL,
    content VARCHAR(255) NOT NULL,
    author_id BIGINT,
    created TIMESTAMP,
    modified TIMESTAMP,

    CONSTRAINT fk_news_author FOREIGN KEY (author_id) REFERENCES author (id)
);

CREATE TABLE news_tag
(
    id BIGSERIAL PRIMARY KEY,
    news_id BIGINT,
    tag_id BIGINT,

    CONSTRAINT fk_news_id FOREIGN KEY (news_id) REFERENCES news (id),
    CONSTRAINT fk_tag_id FOREIGN KEY (tag_id) REFERENCES tag (id)
)

