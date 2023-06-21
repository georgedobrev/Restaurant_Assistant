CREATE TABLE reporting
(
    id INT IDENTITY(1,1) PRIMARY KEY,
    table_id INT NOT NULL,
    restaurant_id INT NOT NULL,
    report_from INT NOT NULL,
    report_to INT NOT NULL,
    FOREIGN KEY (table_id) REFERENCES app_table (id),
    FOREIGN KEY (restaurant_id) REFERENCES restaurant (id),
    FOREIGN KEY (report_from) REFERENCES app_user (id),
    FOREIGN KEY (report_to) REFERENCES app_user (id)
);