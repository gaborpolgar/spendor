drop table transactions;
create table transactions (id bigint NOT NULL auto_increment primary key,
name VARCHAR(255),
amount bigint,
date DATETIME,
location VARCHAR(255));