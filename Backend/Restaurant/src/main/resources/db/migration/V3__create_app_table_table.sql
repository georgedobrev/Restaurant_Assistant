CREATE TABLE app_table
(
    id INT IDENTITY(1,1) PRIMARY KEY,
    table_number INT,
    qr_id INT,
    occupied BIT,
    restaurant_id INT NOT NULL,
    capacity INT,
    virtual_table BIT,
    active BIT,
    CONSTRAINT UC_restaurant_table_number UNIQUE (restaurant_id, table_number),
    FOREIGN KEY (qr_id) REFERENCES qr_code (id),
    FOREIGN KEY (restaurant_id) REFERENCES restaurant (id)
);