INSERT INTO  ${H2_DB_SCHEMA}.board (name)
VALUES ('Board 1'),
       ('Board 2');

INSERT INTO  ${H2_DB_SCHEMA}.card_list (name)
VALUES ('To Do'),
       ('In Progress'),
       ('Done');

INSERT INTO ${H2_DB_SCHEMA}.card (name, describe)
VALUES ('Card 1', 'Description 1'),
       ('Card 2', 'Description 2'),
       ('Card 3', 'Description 3'),
       ('Card 4', 'Description 4');

INSERT INTO ${H2_DB_SCHEMA}.member (name, last_name, email, phone)
VALUES ('John', 'Doe', 'john.doe@example.com', '123456789'),
       ('Jane', 'Smith', 'jane.smith@example.com', '987654321');

INSERT INTO ${H2_DB_SCHEMA}.board_card_list (board_id, card_list_id)
VALUES (1, 1),
       (1, 2),
       (1, 3),
       (2, 1);

INSERT INTO ${H2_DB_SCHEMA}.card_list_card (card_list_id, card_id)
VALUES (1, 1),
       (1, 2),
       (2, 3),
       (3, 4);

INSERT INTO ${H2_DB_SCHEMA}.card_member (card_id, member_id)
VALUES (1, 1),
       (2, 2),
       (3, 1),
       (4, 2);
