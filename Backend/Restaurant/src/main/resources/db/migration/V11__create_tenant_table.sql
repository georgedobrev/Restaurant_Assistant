CREATE TABLE tenant
(
    id INT IDENTITY(1,1) PRIMARY KEY,
    email VARCHAR(255),
    restaurant_id INT,
    name VARCHAR(255),
    surname VARCHAR(255),
    created_at DATETIME,
    blacklisted BIT,
    active BIT,
    FOREIGN KEY (restaurant_id) REFERENCES restaurant (id)
);