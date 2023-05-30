CREATE TABLE [table]
(
    id INT PRIMARY KEY,
    qr_id INT,
    occupied BIT,
    restaurant_id INT,
    capacity INT,
    active BIT,
    virtual_table BIT,
    FOREIGN KEY (qr_id) REFERENCES qr_code (id),
    FOREIGN KEY (restaurant_id) REFERENCES restaurant (id)
);