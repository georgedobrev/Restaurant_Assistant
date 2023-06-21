CREATE TABLE tenant
(
    id            INT IDENTITY(1,1) PRIMARY KEY,
    email         VARCHAR(100),
    restaurant_id INT,
    name          VARCHAR(100),
    surname       VARCHAR(100),
    blacklisted   BIT,
    active        BIT,
    created_at    DATETIMEOFFSET,
    FOREIGN KEY (restaurant_id) REFERENCES restaurant (id)
);