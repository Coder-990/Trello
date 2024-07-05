-- Insert sample data into board
INSERT INTO ${H2_DB_SCHEMA}.board (name)
VALUES ('Board 1'),
       ('Board 2');

-- Insert sample data into card_list
INSERT INTO ${H2_DB_SCHEMA}.card_list (name, board_id)
VALUES ('To Do', 1),
       ('In Progress', 1),
       ('Done', 1);

-- Insert sample data into card
INSERT INTO ${H2_DB_SCHEMA}.card (name, describe, card_list_id)
VALUES ('Card 1', 'Description 1', 1),
       ('Card 2', 'Description 2', 1),
       ('Card 3', 'Description 3', 2),
       ('Card 4', 'Description 4', 3);

-- Insert sample data into member
INSERT INTO ${H2_DB_SCHEMA}.member (name, last_name, email, phone, card_id)
VALUES ('John', 'Doe', 'john.doe@example.com', '123456789', 1),
       ('Jane', 'Smith', 'jane.smith@example.com', '987654321', 2);
