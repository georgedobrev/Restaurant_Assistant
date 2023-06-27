CREATE TABLE app_table
(
    id INT IDENTITY(1,1) PRIMARY KEY,
    table_number INT,
    qr_id INT,
    occupied BIT,
    restaurant_id INT NOT NULL,
    section_id INT,
    created_by NVARCHAR(100) NOT NULL,
    updated_by NVARCHAR(100),
    capacity INT,
    virtual_table BIT,
    active BIT,
    created_at DATETIMEOFFSET NOT NULL,
    updated_at DATETIMEOFFSET,
    CONSTRAINT UQ_app_table_restaurant_id_table_number UNIQUE (restaurant_id, table_number),
    FOREIGN KEY (qr_id) REFERENCES qr_code (id),
    FOREIGN KEY (restaurant_id) REFERENCES restaurant (id),
    FOREIGN KEY (section_id) REFERENCES section (id),
);