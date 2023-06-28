CREATE TABLE section
(
    id INT IDENTITY(1,1) PRIMARY KEY,
    section_name NVARCHAR(50),
    table_numbers NVARCHAR(30),
    restaurant_id INT,
    created_by INT NOT NULL,
    updated_by INT,
    created_at DATETIMEOFFSET NOT NULL,
    updated_at DATETIMEOFFSET,
    CONSTRAINT UQ_section_table_numbers_restaurant_id UNIQUE (table_numbers, restaurant_id),
    FOREIGN KEY (restaurant_id) REFERENCES restaurant(id),
);