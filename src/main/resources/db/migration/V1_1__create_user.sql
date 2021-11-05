CREATE TABLE IF NOT EXISTS users
(
    id           VARCHAR(255) NOT NULL,
    username     VARCHAR(255),
    email        VARCHAR(255),
    display_name VARCHAR(255),
    is_active    BOOLEAN,
    CONSTRAINT pk_users PRIMARY KEY (id)
);
