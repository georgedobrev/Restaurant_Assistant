CREATE TABLE role
(
    role_id INT PRIMARY KEY,
    role_type VARCHAR(10) CHECK (role_type IN ('Admin', 'User', 'Waiter'))
);