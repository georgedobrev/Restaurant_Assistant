CREATE TABLE refresh_token (
    id INT PRIMARY KEY IDENTITY,
    created_by NVARCHAR(100) NOT NULL,
    updated_by NVARCHAR(100),
    app_user_id INT UNIQUE,
    token NVARCHAR(255) UNIQUE NOT NULL,
    created_at DATETIMEOFFSET NOT NULL,
    updated_at DATETIMEOFFSET,
    FOREIGN KEY (app_user_id) REFERENCES app_user(id)
)