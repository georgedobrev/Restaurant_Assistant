CREATE TABLE restaurant
(
    id INT PRIMARY KEY,
    name NVARCHAR(255) NOT NULL,
    tables_count INT,
    address NVARCHAR(255),
    phone_number1 NVARCHAR(20),
    phone_number2 NVARCHAR(20),
    phone_number3 NVARCHAR(20),
    active BIT
);