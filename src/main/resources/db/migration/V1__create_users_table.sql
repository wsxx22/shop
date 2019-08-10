CREATE TABLE IF NOT EXISTS users (
    id bigint auto_increment primary key ,
    login varchar(20) not null unique ,
    password varchar(20) not null ,
    email varchar(20) not null unique
)