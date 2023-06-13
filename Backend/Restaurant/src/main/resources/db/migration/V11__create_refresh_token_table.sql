CREATE TABLE refresh_token (
    id INT PRIMARY KEY IDENTITY,
    token NVARCHAR(255) UNIQUE NOT NULL,
    app_user_id INT FOREIGN KEY REFERENCES app_user(id)
)