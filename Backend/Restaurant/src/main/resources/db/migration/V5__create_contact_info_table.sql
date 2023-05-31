CREATE TABLE contact_info
(
    id INT PRIMARY KEY,
    restaurant_id INT,
    phone_number1 NVARCHAR(20),
    phone_number2 NVARCHAR(20),
    phone_number3 NVARCHAR(20),
    address NVARCHAR(255),
    FOREIGN KEY (restaurant_id) REFERENCES restaurant (id)
);