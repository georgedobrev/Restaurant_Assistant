CREATE TABLE token
(
    id INT PRIMARY KEY,
    expiry_date DATETIME,
    token NVARCHAR(255),
    user_id INT,
    FOREIGN KEY (user_id) REFERENCES [user] (id)
);