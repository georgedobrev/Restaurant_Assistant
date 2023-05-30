CREATE TABLE user_role
(
    user_id INT,
    role_id INT,
    restaurant_id INT,
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES [user] (user_id),
    FOREIGN KEY (role_id) REFERENCES role (role_id),
    FOREIGN KEY (restaurant_id) REFERENCES restaurant (restaurant_id)
);