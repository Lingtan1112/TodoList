drop table user_info;
create table user_info (user_id int primary key AUTO_INCREMENT,username varchar(100) unique, password varchar(100), role varchar(50) default 'USER');

drop table user_roles;
create table user_roles (role_id int primary key, role_name varchar(50), status varchar(50), created_date date default now(), updated_date date default now());
