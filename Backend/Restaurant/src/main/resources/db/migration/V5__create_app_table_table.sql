CREATE TABLE app_table
(
    id INT IDENTITY(1,1) PRIMARY KEY,
    table_number INT,
    qr_id INT,
    restaurant_id INT NOT NULL,
    capacity INT,
    merged_table BIT,
    occupied BIT,
    deleted BIT,
    created_by INT NOT NULL,
    updated_by INT,
    created_at DATETIMEOFFSET NOT NULL,
    updated_at DATETIMEOFFSET,
    CONSTRAINT UQ_app_table_restaurant_id_table_number UNIQUE (restaurant_id, table_number),
    FOREIGN KEY (qr_id) REFERENCES qr_code (id),
    FOREIGN KEY (restaurant_id) REFERENCES restaurant (id)
);