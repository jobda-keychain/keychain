DROP TABLE IF EXISTS `account`;
DROP TABLE IF EXISTS `environment`;
DROP TABLE IF EXISTS `platform`;

CREATE TABLE platform
(
    id   BIGINT     NOT NULL AUTO_INCREMENT,
    name VARCHAR(9) NOT NULL UNIQUE,
    PRIMARY KEY (id)
);

CREATE TABLE environment
(
    id           BIGINT       NOT NULL AUTO_INCREMENT,
    name         VARCHAR(10)  NOT NULL,
    server_domain VARCHAR(255) NOT NULL,
    client_domain VARCHAR(255) NOT NULL,
    platform_id  BIGINT NOT NULL,
    CONSTRAINT uq UNIQUE (name, platform_id),
    PRIMARY KEY (id),
    FOREIGN KEY (platform_id)
        REFERENCES platform (id) ON DELETE CASCADE
);

CREATE TABLE account
(
    id             BIGINT       NOT NULL AUTO_INCREMENT,
    user_id         VARCHAR(20)  NOT NULL,
    password       VARCHAR(20)  NOT NULL,
    description    VARCHAR(100) NOT NULL,
    environment_id BIGINT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (environment_id)
        REFERENCES environment (id) ON DELETE CASCADE

);
