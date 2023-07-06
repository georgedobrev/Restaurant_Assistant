CREATE TABLE merged_table
(
    id INT IDENTITY(1,1) PRIMARY KEY,
    table_numbers NVARCHAR(50),
    restaurant_id INT,
    occupied BIT,
    active BIT,
    created_by INT NOT NULL,
    updated_by INT,
    created_at DATETIMEOFFSET NOT NULL,
    updated_at DATETIMEOFFSET,
    FOREIGN KEY (restaurant_id) REFERENCES restaurant (id)
);