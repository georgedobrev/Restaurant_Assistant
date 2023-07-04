CREATE TABLE waiter_section
(
    section_id INT NOT NULL,
    waiter_id INT NOT NULL,
    shift_id INT NOT NULL,
    created_by INT NOT NULL,
    updated_by INT,
    created_at smalldatetime NOT NULL,
    updated_at smalldatetime,
    FOREIGN KEY (section_id) REFERENCES section (id),
    FOREIGN KEY (waiter_id) REFERENCES app_user (id),
    FOREIGN KEY (shift_id) REFERENCES shift (id),
    PRIMARY KEY (section_id, waiter_id, shift_id)
);