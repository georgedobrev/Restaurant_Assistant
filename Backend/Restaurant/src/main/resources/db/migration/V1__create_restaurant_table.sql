CREATE TABLE restaurant
(
    id INT PRIMARY KEY,
    name NVARCHAR(255),
    tables_count INT,
    address NVARCHAR(255),
    active BIT
);