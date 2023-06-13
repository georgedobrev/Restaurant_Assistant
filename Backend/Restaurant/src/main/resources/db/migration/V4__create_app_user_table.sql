CREATE TABLE app_user
(
    -- todo make email unique
    id INT IDENTITY(1,1) PRIMARY KEY,
    email NVARCHAR(100) NOT NULL UNIQUE,
    password NVARCHAR(100),
    name NVARCHAR(100),
    surname NVARCHAR(100),
    blacklisted BIT,
    active BIT,
    created_at DATETIMEOFFSET
);