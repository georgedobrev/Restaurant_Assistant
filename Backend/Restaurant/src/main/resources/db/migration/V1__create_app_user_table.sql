CREATE TABLE app_user
(
    id INT IDENTITY(1,1) PRIMARY KEY,
    email NVARCHAR(100) UNIQUE NOT NULL,
    password NVARCHAR(100),
    name NVARCHAR(100),
    surname NVARCHAR(100),
    blacklisted BIT,
    active BIT,
    deleted BIT,
    created_by INT,
    updated_by INT,
    created_at DATETIMEOFFSET NOT NULL,
    updated_at DATETIMEOFFSET
);