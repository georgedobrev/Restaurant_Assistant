CREATE TABLE virtual_table
(
    id INT IDENTITY(1,1) PRIMARY KEY,
    table_numbers NVARCHAR(50),
    restaurant_id INT,
    occupied BIT,
    active BIT,
    FOREIGN KEY (restaurant_id) REFERENCES restaurant (id)
);