CREATE TABLE token
(
    id INT PRIMARY KEY,
    expiry_date DATETIME,
    token VARCHAR(255),
    user_id INT,
    FOREIGN KEY (user_id) REFERENCES [user] (user_id)
);