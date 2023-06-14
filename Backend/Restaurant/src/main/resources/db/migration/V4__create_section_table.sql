CREATE TABLE section
(
    id INT IDENTITY(1,1) PRIMARY KEY,
    section_number INT UNIQUE,
    table_numbers NVARCHAR(255) NOT NULL,
    restaurant_id INT,
    waiter_id INT UNIQUE,
    FOREIGN KEY (restaurant_id) REFERENCES restaurant(id),
    FOREIGN KEY (waiter_id) REFERENCES app_user(id)
)
