create table transactions (id bigint NOT NULL auto_increment primary key,
transaction_name VARCHAR(255) NOT NULL,
amount bigint,
date DATETIME,
location VARCHAR(255));