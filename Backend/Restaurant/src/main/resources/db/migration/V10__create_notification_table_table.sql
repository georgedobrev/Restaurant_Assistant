CREATE TABLE notification
(
    id INT IDENTITY(1,1) PRIMARY KEY,
    app_user_id INT NOT NULL,
    app_table_id INT NOT NULL,
    request_type NVARCHAR(6) CHECK (request_type IN ('Menu', 'Bill', 'Waiter')) NOT NULL,
    message NVARCHAR(255),
    approved BIT,
    created_at DATETIMEOFFSET NOT NULL,
    FOREIGN KEY (app_user_id) REFERENCES app_user (id),
    FOREIGN KEY (app_table_id) REFERENCES app_table (id)
);