CREATE TABLE task_statuses(
    id BIGSERIAL NOT NULL PRIMARY KEY UNIQUE,
    sname TEXT NOT NULL UNIQUE,
    scolor TEXT NOT NULL
);

CREATE TABLE event_statuses(
    id BIGSERIAL NOT NULL PRIMARY KEY UNIQUE,
    sname TEXT NOT NULL UNIQUE,
    scolor TEXT NOT NULL
);

CREATE TABLE events(
    id BIGSERIAL NOT NULL PRIMARY KEY,
    sname TEXT NOT NULL,
    slocation TEXT,
    ddate TIMESTAMP NOT NULL,
    status_id BIGINT NOT NULL DEFAULT 1,
    FOREIGN KEY(status_id) REFERENCES event_statuses(id)
);

CREATE TABLE tasks(
    id BIGSERIAL NOT NULL PRIMARY KEY,
    event_id BIGINT NOT NULL,
    sname TEXT NOT NULL,
    sdescription TEXT,
    ddeadline TIMESTAMP NOT NULL,
    status_id BIGINT NOT NULL DEFAULT 1,
    FOREIGN KEY(event_id) REFERENCES events(id),
    FOREIGN KEY(status_id) REFERENCES task_statuses(id)
);



CREATE OR REPLACE PROCEDURE task_status_insert_procedure (
    _sname TEXT,
    _scolor TEXT
)
AS $$
BEGIN
INSERT INTO task_statuses (sname, scolor)
VALUES (_sname, _scolor);
END;
$$
LANGUAGE 'plpgsql';

CREATE OR REPLACE PROCEDURE event_status_insert_procedure (
    _sname TEXT,
    _scolor TEXT
)
AS $$
BEGIN
INSERT INTO event_statuses (sname, scolor)
VALUES (_sname, _scolor);
END;
$$
LANGUAGE 'plpgsql';

CREATE OR REPLACE PROCEDURE event_insert_procedure (
    _event_id BIGINT,
    _sname TEXT,
    _slocation TEXT,
    _ddate TIMESTAMP,
    _status_id BIGINT
)
AS $$
BEGIN
INSERT INTO events (sname, slocation, ddate, status_id)

VALUES (_sname, _slocation, _ddate, _status_id);

END;
$$

LANGUAGE 'plpgsql';

CREATE OR REPLACE PROCEDURE task_insert_procedure (
    _event_id BIGINT,
    _sname TEXT,
    _sdescription TEXT,
    _ddeadline TIMESTAMP,
    _status_id BIGINT
)
AS $$
BEGIN
INSERT INTO tasks (event_id, sname, sdescription, ddeadline, status_id)
--INSERT INTO tasks (sname, sdescription, ddeadline, status_id)
VALUES (_event_id, _sname, _sdescription, _ddeadline, _status_id);
--VALUES (_sname, _sdescription, _ddeadline, _status_id);
END;
$$

LANGUAGE 'plpgsql';


-- CREATE TABLE item_statuses(
--     id SERIAL NOT NULL PRIMARY KEY UNIQUE,
--     sname TEXT NOT NULL UNIQUE,
--     scolor TEXT NOT NULL
-- );
--
-- CREATE OR REPLACE PROCEDURE item_status_insert_procedure (
--     _sname TEXT,
--     _scolor TEXT
-- )
-- AS $$
-- BEGIN
--     INSERT INTO item_statuses (sname, scolor)
--     VALUES (_sname, _scolor);
-- END;
-- $$
-- LANGUAGE 'plpgsql';
--
-- CREATE TABLE items(
--     id SERIAL NOT NULL PRIMARY KEY,
--     event_id BIGINT NOT NULL,
--     sname TEXT NOT NULL,
--     sdescription TEXT,
--     namount BIGINT NOT NULL DEFAULT 0,
--     nprice FLOAT NOT NULL DEFAULT 0,
--     nsumprice FLOAT NOT NULL DEFAULT 0,
--     ddeadline TIMESTAMP NOT NULL,
--     status_id SMALLINT NOT NULL DEFAULT 1,
--     FOREIGN KEY(status_id) REFERENCES item_statuses(id)
-- );
--
-- CREATE OR REPLACE PROCEDURE item_insert_procedure (
--     _event_id BIGINT,
--     _sname TEXT,
--     _sdescription TEXT,
--     _namount BIGINT,
--     _nprice FLOAT,
--     _nsumprice FLOAT,
--     _ddeadline TIMESTAMP,
--     _status_id SMALLINT
-- )
-- AS $$
-- BEGIN
--     INSERT INTO items (event_id, sname, sdescription, namount, nprice, nsumprice, ddeadline, status_id)
--     VALUES (_event_id, _sname, _sdescription, _namount, _nprice, _nsumprice, _ddeadline, _status_id);
-- END;
-- $$
--
-- LANGUAGE 'plpgsql';
