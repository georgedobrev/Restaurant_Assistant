CREATE TABLE user_table
(
    id INT PRIMARY KEY,
    user_id INT NOT NULL,
    waiter_id INT NOT NULL,
    table_id INT NOT NULL,
    start_time DATETIME NOT NULL,
    end_time DATETIME NOT NULL,
    FOREIGN KEY (user_id) REFERENCES [user] (id),
    FOREIGN KEY (waiter_id) REFERENCES [user] (id),
    FOREIGN KEY (table_id) REFERENCES [table] (id)
);