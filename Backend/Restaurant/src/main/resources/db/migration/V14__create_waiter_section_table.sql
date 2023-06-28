CREATE TABLE waiter_section
(
    section_id INT NOT NULL,
    waiter_id INT NOT NULL,
    shift_id INT NOT NULL,
    created_by NVARCHAR(100) NOT NULL,
    updated_by NVARCHAR(100),
    created_at DATETIMEOFFSET NOT NULL,
    updated_at DATETIMEOFFSET,
    FOREIGN KEY (section_id) REFERENCES section (id),
    FOREIGN KEY (waiter_id) REFERENCES app_user (id),
    FOREIGN KEY (shift_id) REFERENCES shift (id),
    PRIMARY KEY (section_id, waiter_id, shift_id)
);