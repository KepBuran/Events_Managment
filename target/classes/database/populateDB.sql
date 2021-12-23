

CALL task_status_insert_procedure (
                            TEXT 'Unknown',
                            TEXT 'Gray'
                          );


CALL task_status_insert_procedure (
                            TEXT 'Getting ready',
                            TEXT 'Yellow'
                          );


CALL task_status_insert_procedure (
                            TEXT 'Ready',
                            TEXT 'Green'
                          );

CALL task_status_insert_procedure (
                            TEXT 'Cancelled',
                            TEXT 'Red'
                          );

CALL task_insert_procedure (BIGINT '1',
                          TEXT 'Check music system',
                          TEXT 'It is very important to do that!',
                          TIMESTAMP '2022-02-04 12:00',
                          BIGINT '2'
                          );

CALL task_insert_procedure (BIGINT '1',
                          TEXT 'Print tickets',
                          NULL,
                          TIMESTAMP '2022-02-02 22:40',
                          BIGINT '2'
                          );

CALL task_insert_procedure (BIGINT '1',
                          TEXT 'Call stadium manager',
                          'Phone number: +380958400976',
                          TIMESTAMP '2022-02-04 12:00',
                          BIGINT '3'
                          );

CALL task_insert_procedure (BIGINT '2',
                          TEXT 'Check field',
                          TEXT 'Check quality of grass',
                          TIMESTAMP '2021-12-12 22:00',
                          BIGINT '2'
                          );

CALL task_insert_procedure (BIGINT '2',
                          TEXT 'Buy ball',
                          NULL,
                          TIMESTAMP '2021-12-12 12:00',
                          BIGINT '3'
                          );

CALL task_insert_procedure (BIGINT '2',
                          TEXT 'Register teams',
                          TEXT 'Create google form and send it to captains',
                          TIMESTAMP '2021-12-12 22:00',
                          BIGINT '2'
                          );

-- CALL task_insert_procedure (--BIGINT '2',
--                           TEXT 'Buy car',
--                           TEXT 'To transport things. Cancelled due to cost',
--                           TIMESTAMP '2021-12-14 12:00',
--                           BIGINT '4'
--                           );