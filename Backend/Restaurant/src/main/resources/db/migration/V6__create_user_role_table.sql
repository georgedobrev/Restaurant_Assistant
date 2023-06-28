CREATE TABLE user_role
(
    app_user_id INT NOT NULL,
    restaurant_id INT,
    role_type NVARCHAR(6) CHECK (role_type IN ('Admin', 'Waiter', 'Tenant')) NOT NULL,
    FOREIGN KEY (app_user_id) REFERENCES app_user (id),
    FOREIGN KEY (restaurant_id) REFERENCES restaurant (id),
    PRIMARY KEY (app_user_id, restaurant_id, role_type)
);