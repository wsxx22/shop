CREATE TABLE IF NOT EXISTS product_categories(
    id_product bigint not null ,
    id_category bigint not null ,

    foreign key (id_product) references products(id),
    foreign key (id_category) references categories(id),
    primary key (id_product, id_category)
)
