CREATE SEQUENCE IF NOT EXISTS user_id_seq;

CREATE TABLE IF NOT EXISTS users
(
    id           int8         NOT NULL PRIMARY KEY,
    login        VARCHAR(30)  NOT NULL UNIQUE,
    password     VARCHAR(100) NOT NULL,
    email        VARCHAR(60)  NOT NULL UNIQUE,
    phone_number VARCHAR(15)  NOT NULL,
    company      VARCHAR(100)
);

ALTER SEQUENCE user_id_seq OWNED BY users.id;

ALTER TABLE users
    ALTER COLUMN id SET DEFAULT nextval('user_id_seq');

CREATE SEQUENCE IF NOT EXISTS message_id_seq;

CREATE TABLE IF NOT EXISTS messages
(
    id           int8         NOT NULL PRIMARY KEY,
    sent_to      VARCHAR(30)  NOT NULL,
    sent_from    VARCHAR(30)  NOT NULL,
    create_time  VARCHAR(30)  NOT NULL,
    text         VARCHAR(300) NOT NULL,
    general_chat BOOLEAN      NOT NULL
);

ALTER SEQUENCE message_id_seq OWNED BY messages.id;

ALTER TABLE messages
    ALTER COLUMN id SET DEFAULT nextval('message_id_seq');