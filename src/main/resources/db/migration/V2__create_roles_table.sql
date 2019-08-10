CREATE TABLE IF NOT EXISTS roles (
    id bigint auto_increment primary key ,
    role varchar(20) not null unique
)