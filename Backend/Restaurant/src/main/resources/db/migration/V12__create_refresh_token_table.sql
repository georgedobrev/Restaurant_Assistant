CREATE TABLE refresh_token (
    id INT PRIMARY KEY IDENTITY,
    app_user_id INT UNIQUE,
    token NVARCHAR(255) UNIQUE NOT NULL,
    created_by INT NOT NULL,
    updated_by INT,
    created_at smalldatetime NOT NULL,
    updated_at smalldatetime,
    FOREIGN KEY (app_user_id) REFERENCES app_user(id)
)