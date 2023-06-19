CREATE TABLE sysadmin
(
    id         INT PRIMARY KEY,
    email      NVARCHAR,
    name       NVARCHAR,
    surname    NVARCHAR,
    active     BOOLEAN,
    created_at DATETIME
);
