CREATE TABLE [table]
(
    table_id INT PRIMARY KEY,
    QR_id INT,
    occupied BIT,
    restaurant_id INT,
    capacity INT,
    active BIT,
    virtual_table BIT,
    FOREIGN KEY (QR_id) REFERENCES QR_code (QR_id),
    FOREIGN KEY (restaurant_id) REFERENCES restaurant (restaurant_id)
);