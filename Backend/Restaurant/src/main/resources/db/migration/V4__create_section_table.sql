CREATE TABLE section
(
    id INT IDENTITY(1,1) PRIMARY KEY,
    section_number INT UNIQUE,
    table_numbers NVARCHAR(255) NOT NULL,
    restaurant_id INT,
    waiter_id INT UNIQUE,
    CONSTRAINT UC_section_table_numbers_restaurant_id UNIQUE (table_numbers, restaurant_id),
    FOREIGN KEY (restaurant_id) REFERENCES restaurant(id),
    FOREIGN KEY (waiter_id) REFERENCES app_user(id)
)
