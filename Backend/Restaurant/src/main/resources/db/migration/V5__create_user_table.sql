CREATE TABLE [user]
(
    id INT PRIMARY KEY,
    email NVARCHAR(100) NOT NULL,
    name NVARCHAR(100),
    surname NVARCHAR(100),
    created_at DATETIME,
    blacklisted BIT,
    active BIT
);