CREATE TABLE notification_table
(
    id INT IDENTITY(1,1) PRIMARY KEY NOT NULL,
    user_id INT NOT NULL,
    table_id INT NOT NULL,
    request_type NVARCHAR(6) CHECK (request_type IN ('Menu', 'Bill', 'Waiter')),
    message NVARCHAR(255),
    approved BIT,
    FOREIGN KEY (user_id) REFERENCES [user] (id)
);