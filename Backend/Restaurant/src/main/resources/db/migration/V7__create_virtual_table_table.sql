CREATE TABLE virtual_table
(
    id INT IDENTITY(1,1) PRIMARY KEY,
    tables_ids NVARCHAR(50),
    created_by INT NOT NULL,
    updated_by INT,
    created_at DATETIMEOFFSET NOT NULL,
    updated_at DATETIMEOFFSET,
    FOREIGN KEY (created_by) REFERENCES app_user (id),
    FOREIGN KEY(updated_by) REFERENCES app_user (id)
);