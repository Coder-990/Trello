package hr.ericsson.sample.trello.services.impl;

import hr.ericsson.sample.trello.exceptions.NotFoundException;
import hr.ericsson.sample.trello.repositories.BoardRepository;
import hr.ericsson.sample.trello.repositories.models.Board;
import hr.ericsson.sample.trello.services.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;

    @Override
    public List<Board> getAllBoards() {
        return boardRepository.findAll();
    }

    @Override
    public Optional<Board> getOneById(Long id) {
        return boardRepository.findById(id);
    }

    @Override
    public Board createBoard(Board board) {
        return boardRepository.save(board);
    }

    @Override
    public Board updateBoard(Long id, Board board) {
        return getOneById(id)
                .map(existingBoard -> {
                    existingBoard.setName(board.getName());
                    existingBoard.setCardLists(board.getCardLists());
                    return boardRepository.save(existingBoard);
                }).orElseThrow(() -> new NotFoundException(("Could not find Board by this id %s").formatted(id)));
    }

    @Override
    public void deleteBoard(Long id) {
        boardRepository.deleteById(id);
    }

}
