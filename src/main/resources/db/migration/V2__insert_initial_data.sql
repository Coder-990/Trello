INSERT INTO PUBLIC.BOARD (name) VALUES ('Sample Board');

INSERT INTO PUBLIC.CARD_LIST (name, board_id) VALUES ('To Do', 1), ('In Progress', 1), ('Done', 1);

INSERT INTO PUBLIC.CARD (text, list_id) VALUES ('Task 1', 1), ('Task 2', 2), ('Task 3', 3);
