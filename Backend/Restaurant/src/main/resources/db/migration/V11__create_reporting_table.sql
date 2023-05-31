CREATE TABLE reporting
(
    id INT PRIMARY KEY,
    table_id INT NOT NULL,
    restaurant_id INT NOT NULL,
    report_from INT NOT NULL,
    report_to INT NOT NULL,
    FOREIGN KEY (table_id) REFERENCES [table] (id),
    FOREIGN KEY (restaurant_id) REFERENCES restaurant (id),
    FOREIGN KEY (report_from) REFERENCES [user] (id),
    FOREIGN KEY (report_to) REFERENCES [user] (id)
);