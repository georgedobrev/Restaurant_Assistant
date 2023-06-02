CREATE TABLE reservation
(
    id INT IDENTITY(1,1) PRIMARY KEY,
    restaurant_id INT NOT NULL,
    table_id INT,
    people_count INT,
    reservation_time DATETIME,
    client_phone_number NVARCHAR(20),
    FOREIGN KEY (restaurant_id) REFERENCES restaurant(id),
    FOREIGN KEY (table_id) REFERENCES [table] (id)
);