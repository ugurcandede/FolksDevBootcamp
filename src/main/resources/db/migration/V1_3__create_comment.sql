CREATE TABLE IF NOT EXISTS comment
(
    id            VARCHAR(255) NOT NULL,
    body          VARCHAR(255),
    created_at    TIMESTAMP,
    updated_at    TIMESTAMP,
    post_id       VARCHAR(255) NOT NULL,
    author_id     VARCHAR(255) NOT NULL
);
