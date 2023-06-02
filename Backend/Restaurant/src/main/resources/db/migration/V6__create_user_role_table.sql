CREATE TABLE user_role
(
    app_user_id INT NOT NULL,
    restaurant_id INT NOT NULL,
    role_id INT NOT NULL,
    FOREIGN KEY (app_user_id) REFERENCES app_user (id),
    FOREIGN KEY (restaurant_id) REFERENCES restaurant (id),
    FOREIGN KEY (role_id) REFERENCES role (id),
    PRIMARY KEY (app_user_id, restaurant_id, role_id)
);