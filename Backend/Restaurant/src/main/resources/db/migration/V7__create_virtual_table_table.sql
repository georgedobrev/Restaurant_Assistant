CREATE TABLE virtual_table
(
    id INT IDENTITY(1,1) PRIMARY KEY,
    table_numbers NVARCHAR(50),
    occupied BIT,
    restaurant_id INT,
    active BIT,
    FOREIGN KEY (restaurant_id) REFERENCES restaurant (id)
);