CREATE TABLE IF NOT EXISTS user
(
    id         int          NOT NULL AUTO_INCREMENT primary key,
    first_name varchar(255) NOT NULL,
    last_name  varchar(255) NOT NULL,
    password   varchar(255) NOT NULL

) ENGINE = InnoDB
  DEFAULT CHARSET = UTF8;
