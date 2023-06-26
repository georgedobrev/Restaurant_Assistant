CREATE TABLE notification
(
    id INT IDENTITY(1,1) PRIMARY KEY,
    created_by INT NOT NULL,
    updated_by INT,
    app_table_id INT NOT NULL,
    request_type NVARCHAR(6) CHECK (request_type IN ('Menu', 'Bill', 'Waiter')) NOT NULL,
    message NVARCHAR(255),
    approved BIT,
    created_at DATETIMEOFFSET NOT NULL,
    updated_at DATETIMEOFFSET,
    FOREIGN KEY (created_by) REFERENCES app_user (id),
    FOREIGN KEY(updated_by) REFERENCES app_user (id),
    FOREIGN KEY (app_table_id) REFERENCES app_table (id)
);