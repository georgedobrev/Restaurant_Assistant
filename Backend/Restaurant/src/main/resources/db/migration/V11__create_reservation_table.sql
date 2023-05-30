CREATE TABLE reservation
(
    id INT PRIMARY KEY,
    restaurant_id INT,
    reservation_time DATETIME,
    client_phone_number INT,
    table_id INT,
    people_count INT,
    FOREIGN KEY (restaurant_id) REFERENCES restaurant(id),
    FOREIGN KEY (table_id) REFERENCES [table] (id)
);