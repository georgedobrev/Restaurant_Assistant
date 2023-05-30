CREATE TABLE [user]
(
    user_id INT PRIMARY KEY,
    email VARCHAR(255),
    name VARCHAR(255),
    surname VARCHAR(255),
    created DATETIME,
    blacklisted BIT,
    table_id INT,
    active INT,
    FOREIGN KEY (table_id) REFERENCES [table] (table_id)
);