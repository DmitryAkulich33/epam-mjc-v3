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

    CONSTRAINT fk_news_id FOREIGN KEY (news_id) REFERENCES news (id) ON DELETE CASCADE,
    CONSTRAINT fk_tag_id FOREIGN KEY (tag_id) REFERENCES tag (id) ON DELETE CASCADE
);

CREATE TABLE comment
(
    id BIGSERIAL PRIMARY KEY,
    content VARCHAR(255) NOT NULL,
    news_id BIGINT,
    created TIMESTAMP,
    modified TIMESTAMP,

    CONSTRAINT fk_news_comment FOREIGN KEY (news_id) REFERENCES news (id) ON DELETE CASCADE
);

\connect newsmanagement
CREATE TABLE usr
(
    id BIGSERIAL PRIMARY KEY,
    login VARCHAR(15) UNIQUE NOT NULL,
    password VARCHAR(15) NOT NULL
);

\connect newsmanagement
CREATE TABLE role
(
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(15) UNIQUE NOT NULL
);

\connect newsmanagement
CREATE TABLE user_roles
(
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT,
    role_id BIGINT,

    CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES usr (id) ON DELETE CASCADE,
    CONSTRAINT fk_role_id FOREIGN KEY (role_id) REFERENCES role (id) ON DELETE CASCADE
);

