CREATE TABLE restaurant
(
    restaurant_id INT PRIMARY KEY,
    name VARCHAR(255),
    address VARCHAR(255),
    tables_count INT,
    active BIT
);