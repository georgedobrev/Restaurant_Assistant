CREATE TABLE user_table
(
    user_id INT,
    datetime_start DATETIME,
    datetime_end DATETIME,
    waiter_id INT,
    table_id INT,
    FOREIGN KEY (user_id) REFERENCES [user] (user_id),
    FOREIGN KEY (waiter_id) REFERENCES [user] (user_id),
    FOREIGN KEY (table_id) REFERENCES [table] (table_id),
    PRIMARY KEY (user_id, datetime_start, datetime_end)
);