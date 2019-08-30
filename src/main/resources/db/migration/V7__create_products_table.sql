CREATE TABLE IF NOT EXISTS products(
    id bigint auto_increment primary key,
    name varchar(30) not null unique ,
    description varchar(200) not null ,
    capacity double not null ,
    price decimal(5,2) not null ,
    amount int not null
)
