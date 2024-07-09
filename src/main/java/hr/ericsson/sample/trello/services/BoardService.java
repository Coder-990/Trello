package hr.ericsson.sample.trello.services;

import hr.ericsson.sample.trello.repositories.models.Board;

import java.util.List;
import java.util.Optional;

public interface BoardService {
    List<Board> getAllBoards();

    Optional<Board> getOneById(Long id);

    Board createBoard(Board board);

    Board updateBoard(Long id, Board board);

    void deleteBoard(Long id);
}
