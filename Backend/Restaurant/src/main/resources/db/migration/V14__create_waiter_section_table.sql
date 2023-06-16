CREATE TABLE waiter_section
(
    id INT IDENTITY(1,1) PRIMARY KEY,
    section_id INT NOT NULL,
    waiter_id INT NOT NULL,
    shift_id INT NOT NULL,
    FOREIGN KEY (section_id) REFERENCES section (id),
    FOREIGN KEY (waiter_id) REFERENCES app_user (id),
    FOREIGN KEY (shift_id) REFERENCES shift (id)
);