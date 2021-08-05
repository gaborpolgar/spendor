drop table if exists transactions;
create table transactions (id bigint NOT NULL auto_increment primary key,
name VARCHAR(255),
amount bigint,
date DATETIME,
location VARCHAR(255));
insert into transactions (name, amount, date, location) values ('élelmiszer', '15000', '2021-08-03T22:02:00', 'lidl' );