CREATE TABLE platform
(
    id   BIGINT     NOT NULL generated by default as identity,
    name VARCHAR(9) NOT NULL UNIQUE,
    PRIMARY KEY (id)
);
CREATE TABLE environment
(
    id           BIGINT       NOT NULL generated by default as identity,
    name         VARCHAR(10)  NOT NULL,
    server_domain VARCHAR(255) NOT NULL,
    client_domain VARCHAR(255) NOT NULL,
    platform_id  VARCHAR(255) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (platform_id)
        REFERENCES platform (id) ON DELETE CASCADE

);
CREATE TABLE account
(
    id             BIGINT       NOT NULL generated by default as identity,
    user_id         VARCHAR(20)  NOT NULL,
    password       VARCHAR(20)  NOT NULL,
    description    VARCHAR(100) NOT NULL,
    environment_id VARCHAR(255) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (environment_id)
        REFERENCES environment (id) ON DELETE CASCADE

);