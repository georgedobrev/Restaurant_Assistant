CREATE TABLE notification_table
(
    id INT IDENTITY(1,1) PRIMARY KEY NOT NULL,
    app_user_id INT NOT NULL,
    app_table_id INT NOT NULL,
    request_type NVARCHAR(6) CHECK (request_type IN ('Menu', 'Bill', 'Waiter')),
    message NVARCHAR(255),
    approved BIT,
    FOREIGN KEY (app_user_id) REFERENCES app_user (id),
    FOREIGN KEY (app_table_id) REFERENCES app_table (id)
);