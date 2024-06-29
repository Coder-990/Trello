CREATE TABLE PUBLIC.BOARD (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       name VARCHAR(255)
);

CREATE TABLE PUBLIC.CARD_LIST (
                           id BIGINT AUTO_INCREMENT PRIMARY KEY,
                           name VARCHAR(255),
                           board_id BIGINT,
                           FOREIGN KEY (board_id) REFERENCES BOARD (id)
);

CREATE TABLE PUBLIC.CARD (
                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                      text VARCHAR(255),
                      list_id BIGINT,
                      FOREIGN KEY (list_id) REFERENCES CARD_LIST(id)
);