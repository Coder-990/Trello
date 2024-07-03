package hr.ericsson.sample.trello.services.impl;

import hr.ericsson.sample.trello.repositories.BoardRepository;
import hr.ericsson.sample.trello.repositories.models.Board;
import hr.ericsson.sample.trello.services.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;

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
        var existingBoard = getOneById(id);
        existingBoard.setName(board.getName());
        existingBoard.setCardLists(board.getCardLists());
        return boardRepository.saveAndFlush(existingBoard);
    }

    @Override
    public void deleteBoard(Long id) {
        boardRepository.deleteById(id);
    }

}
