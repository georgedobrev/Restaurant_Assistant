CREATE TABLE reservation
(
    id INT IDENTITY(1,1) PRIMARY KEY,
    restaurant_id INT NOT NULL,
    app_table_id INT,
    created_by INT NOT NULL,
    updated_by INT,
    people_count INT,
    reservation_time DATETIMEOFFSET,
    client_phone_number NVARCHAR(20),
    created_at DATETIMEOFFSET NOT NULL,
    updated_at DATETIMEOFFSET,
    FOREIGN KEY (restaurant_id) REFERENCES restaurant (id),
    FOREIGN KEY (app_table_id) REFERENCES app_table (id),
    FOREIGN KEY (created_by) REFERENCES app_user (id),
    FOREIGN KEY(updated_by) REFERENCES app_user (id)
);