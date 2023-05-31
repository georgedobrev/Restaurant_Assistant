CREATE TABLE [user]
(
    id INT PRIMARY KEY,
    email NVARCHAR(100),
    name NVARCHAR(100),
    surname NVARCHAR(100),
    created_at DATETIME,
    table_id INT,
    blacklisted BIT,
    active BIT,
    FOREIGN KEY (table_id) REFERENCES [table] (id)
);