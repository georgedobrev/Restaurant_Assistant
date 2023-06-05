CREATE TABLE role
(
    id INT IDENTITY(1,1) PRIMARY KEY,
    type NVARCHAR(8) CHECK (type IN ('Admin', 'User', 'Waiter', 'Sysadmin')) NOT NULL
);