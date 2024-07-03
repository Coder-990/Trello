package hr.ericsson.sample.trello.services;

import hr.ericsson.sample.trello.repositories.models.Board;

import java.util.List;

public interface BoardService {
    List<Board> getAllBoards();

    Board getOneById(Long id);

    Board createBoard(Board board);

    Board updateBoard(Long id, Board board);

    void deleteBoard(Long id);
}
