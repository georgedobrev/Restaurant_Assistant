CREATE TABLE sysadmin
(
    id         INT IDENTITY(1,1) PRIMARY KEY,
    email      VARCHAR(100),
    name       VARCHAR(100),
    surname    VARCHAR(100),
    active     BIT,
    created_at smalldatetime
)
