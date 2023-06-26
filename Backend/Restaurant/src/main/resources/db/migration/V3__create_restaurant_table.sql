CREATE TABLE restaurant
(
    id INT IDENTITY(1,1) PRIMARY KEY ,
    name NVARCHAR(255) NOT NULL,
    created_by INT NOT NULL,
    updated_by INT,
    tables_count INT,
    address NVARCHAR(255),
    phone_number1 NVARCHAR(20),
    phone_number2 NVARCHAR(20),
    phone_number3 NVARCHAR(20),
    active BIT,
    created_at DATETIMEOFFSET NOT NULL,
    updated_at DATETIMEOFFSET,
    FOREIGN KEY (created_by) REFERENCES app_user (id),
    FOREIGN KEY(updated_by) REFERENCES app_user (id)
);