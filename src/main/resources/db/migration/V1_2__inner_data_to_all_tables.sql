insert into file(location) values ('D:\\Files\\first.txt');
insert into file(location) values ('D:\\Files\\second.txt');
insert into file(location) values ('D:\\Files\\third.txt');

insert into user(first_name, last_name, password) VALUES ('Sasha','Ivanov','123456');
insert into user(first_name, last_name, password) VALUES ('Dima','Dmitrov','654321');
insert into user(first_name, last_name, password) VALUES ('Ivan','Sidorov','789987');

insert into event(value, file_id, user_id) VALUES ('add',1,1);
insert into event(value, file_id, user_id) VALUES ('remove',2,2);
insert into event(value, file_id, user_id) VALUES ('update',3,3);