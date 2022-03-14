CREATE TABLE IF NOT EXISTS file
(
    id        int NOT NULL AUTO_INCREMENT primary key,
    location varchar(255) NOT NULL
)ENGINE =InnoDB DEFAULT CHARSET = UTF8;
CREATE TABLE IF NOT EXISTS event
(
    id         int NOT NULL AUTO_INCREMENT PRIMARY KEY ,
    value varchar(255),
    file_id  int  ,
    user_id    int  ,
    foreign key (user_id) references user (id)
        on delete cascade,
    foreign key (file_id) references file(id)
        on delete cascade
);