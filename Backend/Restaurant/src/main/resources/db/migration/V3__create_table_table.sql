CREATE TABLE [table]
(
    id INT PRIMARY KEY,
    name NVARCHAR(100),
    qr_id INT,
    occupied BIT,
    restaurant_id INT NOT NULL,
    capacity INT,
    virtual_table BIT,
    active BIT,
    FOREIGN KEY (qr_id) REFERENCES qr_code (id),
    FOREIGN KEY (restaurant_id) REFERENCES restaurant (id)
);