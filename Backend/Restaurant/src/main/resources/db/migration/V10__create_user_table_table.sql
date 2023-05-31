CREATE TABLE user_table
(
    user_id INT,
    waiter_id INT,
    table_id INT,
    start_time DATETIME,
    end_time DATETIME,
    FOREIGN KEY (user_id) REFERENCES [user] (id),
    FOREIGN KEY (waiter_id) REFERENCES [user] (id),
    FOREIGN KEY (table_id) REFERENCES [table] (id),
    PRIMARY KEY (user_id, start_time, end_time)
);