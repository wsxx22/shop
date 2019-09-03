CREATE TABLE IF NOT EXISTS order_products (
    id_order BIGINT NOT NULL ,
    id_product BIGINT NOT NULL ,

    FOREIGN KEY (id_order) REFERENCES orders(id),
    FOREIGN KEY (id_product) REFERENCES products(id)
)