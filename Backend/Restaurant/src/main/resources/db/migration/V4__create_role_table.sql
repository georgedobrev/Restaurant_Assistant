CREATE TABLE role
(
    id INT IDENTITY(1,1) PRIMARY KEY,
    type NVARCHAR(6) CHECK (type IN ('Admin', 'User', 'Waiter')) NOT NULL
);