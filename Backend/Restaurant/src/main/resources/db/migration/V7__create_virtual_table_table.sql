CREATE TABLE virtual_table
(
    id INT IDENTITY(1,1) PRIMARY KEY,
    tables_ids NVARCHAR(50),
    created_by NVARCHAR(100) NOT NULL,
    updated_by NVARCHAR(100),
    created_at DATETIMEOFFSET NOT NULL,
    updated_at DATETIMEOFFSET,
);