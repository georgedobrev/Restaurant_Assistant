CREATE TABLE role
(
    id INT PRIMARY KEY,
    type NVARCHAR(6) CHECK (type IN ('Admin', 'User', 'Waiter')) NOT NULL
);