CREATE TABLE reporting
(
    report_id INT PRIMARY KEY,
    QR_id INT,
    restaurant_id INT,
    report_from INT,
    report_to INT,
    FOREIGN KEY (QR_id) REFERENCES QR_code (QR_id),
    FOREIGN KEY (restaurant_id) REFERENCES restaurant (restaurant_id),
    FOREIGN KEY (report_from) REFERENCES [user] (user_id),
    FOREIGN KEY (report_to) REFERENCES [user] (user_id)
);