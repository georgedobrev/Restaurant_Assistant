CREATE TABLE shift
(
    id INT IDENTITY(1,1) PRIMARY KEY,
    restaurant_id INT NOT NULL,
    created_by INT NOT NULL,
    updated_by INT,
    start_time TIME NOT NULL,
    end_time TIME NOT NULL,
    day_from NVARCHAR(9) CHECK (day_from IN ('Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday', 'Sunday')) NOT NULL,
    day_to NVARCHAR(9) CHECK (day_to IN ('Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday', 'Sunday')) NOT NULL,
    created_at DATETIMEOFFSET NOT NULL,
    updated_at DATETIMEOFFSET,
    FOREIGN KEY (restaurant_id) REFERENCES restaurant (id),
    CONSTRAINT UQ_shift_restaurant_id_start_time_end_time_day_from_day_to UNIQUE (restaurant_id, start_time, end_time, day_from, day_to),
    FOREIGN KEY (created_by) REFERENCES app_user (id),
    FOREIGN KEY(updated_by) REFERENCES app_user (id),
)