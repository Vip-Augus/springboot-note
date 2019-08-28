create database test collate utf8_general_ci;

create table book
(
	id int auto_increment
		primary key,
	name varchar(120) null,
	author varchar(120) null,
	price decimal(16,5) null
);


INSERT INTO test.book (id, name, author, price) VALUES (1, 'Go 语言实战', 'nonename', 100.00000);