CREATE TABLE tenant
(
    id            INT IDENTITY(1,1) PRIMARY KEY,
    email         VARCHAR(100) UNIQUE NOT NULL
);