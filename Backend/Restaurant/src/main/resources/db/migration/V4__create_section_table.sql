CREATE TABLE section
(
    id INT IDENTITY(1,1) PRIMARY KEY,
    section_name NVARCHAR(255),
    table_numbers NVARCHAR(50),
    restaurant_id INT,
    waiter_id INT,
    FOREIGN KEY (restaurant_id) REFERENCES restaurant(id),
    FOREIGN KEY (waiter_id) REFERENCES app_user(id)
)