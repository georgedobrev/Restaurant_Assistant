CREATE TABLE reservation
(
    id INT PRIMARY KEY,
    restaurant_id INT,
    table_id INT,
    people_count INT,
    reservation_time DATETIME,
    client_phone_number INT,
    FOREIGN KEY (restaurant_id) REFERENCES restaurant(id),
    FOREIGN KEY (table_id) REFERENCES [table] (id)
);