CREATE TABLE notification_table
(
    table_id INT PRIMARY KEY,
    user_id INT,
    request_type NVARCHAR(6) CHECK (request_type IN ('Menu', 'Bill', 'Waiter')),
    message NVARCHAR(255),
    status NVARCHAR(255),
    FOREIGN KEY (user_id) REFERENCES [user] (id),
);