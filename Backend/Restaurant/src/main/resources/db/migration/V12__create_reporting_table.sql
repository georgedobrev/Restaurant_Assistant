CREATE TABLE reporting
(
    id INT PRIMARY KEY,
    table_id INT,
    restaurant_id INT,
    report_from INT,
    report_to INT,
    FOREIGN KEY (table_id) REFERENCES [table] (id),
    FOREIGN KEY (restaurant_id) REFERENCES restaurant (id),
    FOREIGN KEY (report_from) REFERENCES [user] (id),
    FOREIGN KEY (report_to) REFERENCES [user] (id)
);