package hr.ericsson.sample.trello.services.impl;

import hr.ericsson.sample.trello.repositories.BoardRepository;
import hr.ericsson.sample.trello.repositories.models.Board;
import hr.ericsson.sample.trello.services.BoardService;
import hr.ericsson.sample.trello.services.CardListService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;
    private final CardListService cardListService;

    @Override
    public List<Board> getAllBoards() {
        return boardRepository.findAll();
    }

    @Override
    public Board getOneById(Long id) {
        return boardRepository.getReferenceById(id);
    }

    @Override
    public Board createBoard(Board board) {
        return boardRepository.save(board);
    }

    @Override
    public Board updateBoard(Long id, Board board) {
        return boardRepository.findById(id)
                .map(existingBoard -> {
                    existingBoard.setName(board.getName());
                    existingBoard.setCardLists(board.getCardLists());
                    return boardRepository.save(existingBoard);
                }).orElseThrow(() -> new RuntimeException("Board not found"));
    }

    @Override
    public void deleteBoard(Long id) {
        boardRepository.deleteById(id);
    }

}
