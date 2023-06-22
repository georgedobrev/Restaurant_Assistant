CREATE TABLE virtual_table_mapping
(
    id INT IDENTITY(1,1) PRIMARY KEY,
    table_id INT,
    FOREIGN KEY (table_id) REFERENCES virtual_table (id)
)