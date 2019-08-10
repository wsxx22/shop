CREATE TABLE IF NOT EXISTS user_roles(
    id_user bigint not null ,
    id_role bigint not null ,

    foreign key (id_user) references users(id),
    foreign key (id_role) references roles(id),
    primary key (id_user, id_role)
)