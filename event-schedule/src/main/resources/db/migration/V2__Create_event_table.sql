CREATE SEQUENCE event_id_seq START WITH 1 INCREMENT BY 1 NO MINVALUE NO MAXVALUE CACHE 1;

CREATE TABLE events (
    id INTEGER PRIMARY KEY DEFAULT nextval('event_id_seq'),
    name VARCHAR(255) NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    active BOOLEAN NOT NULL,
    institution_id INTEGER NOT NULL,

    CONSTRAINT fk_institution FOREIGN KEY (institution_id) REFERENCES institutions(id)
);
