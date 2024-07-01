INSERT INTO ${H2_DB_SCHEMA}.board (name) VALUES ('Project Alpha');
INSERT INTO ${H2_DB_SCHEMA}.board (name) VALUES ('Project Beta');

INSERT INTO ${H2_DB_SCHEMA}.card_list (name, board_id) VALUES ('To Do', 1);
INSERT INTO ${H2_DB_SCHEMA}.card_list (name, board_id) VALUES ('In Progress', 1);
INSERT INTO ${H2_DB_SCHEMA}.card_list (name, board_id) VALUES ('Done', 1);
INSERT INTO ${H2_DB_SCHEMA}.card_list (name, board_id) VALUES ('Backlog', 2);
INSERT INTO ${H2_DB_SCHEMA}.card_list (name, board_id) VALUES ('Review', 2);

INSERT INTO ${H2_DB_SCHEMA}.card (text, card_list_id) VALUES ('Set up project repository', 1);
INSERT INTO ${H2_DB_SCHEMA}.card (text, card_list_id) VALUES ('Create initial project structure', 1);
INSERT INTO ${H2_DB_SCHEMA}.card (text, card_list_id) VALUES ('Develop core features', 2);
INSERT INTO ${H2_DB_SCHEMA}.card (text, card_list_id) VALUES ('Write documentation', 3);
INSERT INTO ${H2_DB_SCHEMA}.card (text, card_list_id) VALUES ('Plan next sprint', 4);
INSERT INTO ${H2_DB_SCHEMA}.card (text, card_list_id) VALUES ('Code review', 5);