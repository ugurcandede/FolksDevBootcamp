CREATE TABLE IF NOT EXISTS comment
(
    id            VARCHAR(255) NOT NULL,
    body          VARCHAR(255),
    creation_date TIMESTAMP,
    post_id       VARCHAR(255) NOT NULL,
    author_id     VARCHAR(255) NOT NULL
);
