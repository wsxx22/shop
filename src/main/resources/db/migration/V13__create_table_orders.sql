CREATE TABLE IF NOT EXISTS orders (
    id BIGINT AUTO_INCREMENT PRIMARY KEY ,
    id_customer BIGINT NOT NULL ,
    order_date datetime,
    price DECIMAL(7,2) NOT NULL ,

    FOREIGN KEY (id_customer) REFERENCES users(id)
);
