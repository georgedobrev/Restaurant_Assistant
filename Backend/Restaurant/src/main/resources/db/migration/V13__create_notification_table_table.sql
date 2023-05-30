CREATE TABLE notification_table
(
    table_id INT PRIMARY KEY,
    user_id INT,
    request_type VARCHAR(10) CHECK (request_type IN ('Menu', 'Bill', 'Waiter')),
    message VARCHAR(255),
    status VARCHAR(255),
    FOREIGN KEY (user_id) REFERENCES [user] (user_id),
);