CREATE TABLE merged_table
(
    id INT IDENTITY(1,1) PRIMARY KEY,
    table_numbers NVARCHAR(50),
    restaurant_id INT,
    occupied BIT,
    active BIT,
    created_by INT NOT NULL,
    updated_by INT,
    created_at smalldatetime NOT NULL,
    updated_at smalldatetime,
    FOREIGN KEY (restaurant_id) REFERENCES restaurant (id)
);