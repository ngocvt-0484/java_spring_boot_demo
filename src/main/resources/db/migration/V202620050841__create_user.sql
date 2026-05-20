CREATE TABLE users(
    id bigint unsigned auto_increment PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    phone VARCHAR(20) NOT NULL,
    address VARCHAR(255) NOT NULL,
    image VARCHAR(255) NOT NULL,
    user_catalogue_id bigint unsigned default NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    CONSTRAINT fk_user_catalogue FOREIGN KEY (user_catalogue_id) REFERENCES user_catalogues(id) ON DELETE SET NULL
);