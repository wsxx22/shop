create table if not exists products(
    id bigint auto_increment primary key,
    name varchar(30) not null ,
    description varchar(200) not null ,
    amount double,
    price decimal(5,2)
)