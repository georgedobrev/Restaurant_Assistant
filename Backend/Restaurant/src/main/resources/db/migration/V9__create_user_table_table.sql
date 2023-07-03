CREATE TABLE user_table
(
    id INT IDENTITY(1,1) PRIMARY KEY,
    app_user_id INT NOT NULL,
    waiter_ids NVARCHAR(50) NOT NULL,
    app_table_id INT,
    merged_table_id INT,
    start_time DATETIMEOFFSET NOT NULL,
    end_time DATETIMEOFFSET,
    FOREIGN KEY (app_user_id) REFERENCES app_user (id),
    FOREIGN KEY (app_table_id) REFERENCES app_table (id),
    FOREIGN KEY (merged_table_id) REFERENCES merged_table (id)
);