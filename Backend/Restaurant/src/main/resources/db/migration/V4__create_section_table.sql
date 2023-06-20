CREATE TABLE section
(
    id INT IDENTITY(1,1) PRIMARY KEY,
    section_name NVARCHAR(50),
    table_numbers NVARCHAR(30),
    restaurant_id INT,
    CONSTRAINT UQ_section_table_numbers_restaurant_id UNIQUE (table_numbers, restaurant_id),
    FOREIGN KEY (restaurant_id) REFERENCES Restaurant(id)
);