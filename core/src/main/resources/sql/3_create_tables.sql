\connect newsmanagement
CREATE TABLE tag
(
    id   BIGSERIAL PRIMARY KEY,
    name VARCHAR(15) UNIQUE
);