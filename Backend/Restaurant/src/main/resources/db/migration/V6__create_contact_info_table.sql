CREATE TABLE contact_info
(
    contact_id INT PRIMARY KEY,
    restaurant_id INT,
    phone_number1 VARCHAR(255),
    phone_number2 VARCHAR(255),
    phone_number3 VARCHAR(255),
    address VARCHAR(255),
    FOREIGN KEY (restaurant_id) REFERENCES restaurant (restaurant_id)
);