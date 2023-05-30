CREATE TABLE [user]
(
    id INT PRIMARY KEY,
    email NVARCHAR(100),
    name NVARCHAR(100),
    surname NVARCHAR(100),
    created DATETIME,
    blacklisted BIT,
    table_id INT,
    active INT,
    FOREIGN KEY (table_id) REFERENCES [table] (id)
);