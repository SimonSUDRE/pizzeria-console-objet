#create database if not exists Pizzeria;
use bntwwkq07;

drop table if exists Pizza;

create table Pizza (
  Pizza_id varchar(5) not null, 
  Pizza_name varchar(30), 
  Pizza_price double, 
  Pizza_category varchar(30),
  primary key (Pizza_id)
) engine=innodb default charset=utf8;

set autocommit=1;

