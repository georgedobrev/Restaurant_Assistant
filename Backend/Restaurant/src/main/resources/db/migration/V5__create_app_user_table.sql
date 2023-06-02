CREATE TABLE app_user
(
    id INT IDENTITY(1,1) PRIMARY KEY,
    email NVARCHAR(100) NOT NULL,
    name NVARCHAR(100),
    surname NVARCHAR(100),
    created_at DATETIME,
    blacklisted BIT,
    active BIT
);