CREATE TABLE audit (
    id INT IDENTITY(1,1) PRIMARY KEY,
    created_by INT,
    updated_by INT,
    created_at DATETIMEOFFSET,
    updated_at DATETIMEOFFSET,
    FOREIGN KEY (created_by) REFERENCES app_user (id),
    FOREIGN KEY (updated_by) REFERENCES app_user (id)
);