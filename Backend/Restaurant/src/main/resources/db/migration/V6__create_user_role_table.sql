CREATE TABLE user_role
(
    app_user_id INT NOT NULL,
    restaurant_id INT NOT NULL,
    role_type NVARCHAR(6) CHECK (role_type IN ('Admin', 'Waiter')) NOT NULL,
    created_by NVARCHAR(100) NOT NULL,
    updated_by NVARCHAR(100),
    created_at DATETIMEOFFSET NOT NULL,
    updated_at DATETIMEOFFSET,
    FOREIGN KEY (app_user_id) REFERENCES app_user (id),
    FOREIGN KEY (restaurant_id) REFERENCES restaurant (id),
    PRIMARY KEY (app_user_id, restaurant_id, role_type)
);