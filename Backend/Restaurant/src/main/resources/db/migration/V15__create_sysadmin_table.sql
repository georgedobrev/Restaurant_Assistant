CREATE TABLE sysadmin
(
    id         INT IDENTITY(1,1) PRIMARY KEY,
    email      VARCHAR(255),
    name       VARCHAR(255),
    surname    VARCHAR(255),
    active     BIT,
    created_at DATETIMEOFFSET
)
