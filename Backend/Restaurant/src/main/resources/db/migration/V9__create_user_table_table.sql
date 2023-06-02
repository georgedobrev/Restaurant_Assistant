CREATE TABLE user_table
(
    id INT IDENTITY(1,1) PRIMARY KEY,
    app_user_id INT NOT NULL,
    waiter_id INT NOT NULL,
    app_table_id INT NOT NULL,
    start_time DATETIME NOT NULL,
    end_time DATETIME NOT NULL,
    FOREIGN KEY (app_user_id) REFERENCES app_user (id),
    FOREIGN KEY (waiter_id) REFERENCES app_user (id),
    FOREIGN KEY (app_table_id) REFERENCES app_table (id)
);