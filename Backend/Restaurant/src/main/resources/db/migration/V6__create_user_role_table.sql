CREATE TABLE user_role
(
    user_id INT NOT NULL,
    restaurant_id INT NOT NULL,
    role_id INT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES [user] (id),
    FOREIGN KEY (restaurant_id) REFERENCES restaurant (id),
    FOREIGN KEY (role_id) REFERENCES role (id),
    PRIMARY KEY (user_id, restaurant_id, role_id)
);