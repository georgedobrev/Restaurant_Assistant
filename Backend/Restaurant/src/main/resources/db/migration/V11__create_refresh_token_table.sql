CREATE TABLE refresh_token (
    id INT PRIMARY KEY IDENTITY,
    app_user_id INT,
    token NVARCHAR(255) UNIQUE NOT NULL,
    FOREIGN KEY (app_user_id) REFERENCES app_user(id)
)