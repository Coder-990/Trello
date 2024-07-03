package hr.ericsson.sample.trello.controllers;

import hr.ericsson.sample.trello.controllers.request.AddBoardRequest;
import hr.ericsson.sample.trello.controllers.request.ModifyBoardRequest;
import hr.ericsson.sample.trello.controllers.response.BoardResponse;
import hr.ericsson.sample.trello.mappers.BoardMapper;
import hr.ericsson.sample.trello.services.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/boards")
public class BoardController {

    private final BoardService boardService;
    private final BoardMapper boardMapper;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<BoardResponse> fetchAllBoards() {
        log.info("Fetching all boards from sample trello ...");
        var boards = boardService.getAllBoards();
        var boardListResponse = boardMapper.toBoardListResponse(boards);
        log.info("Fetched all boards with body {}...", boardListResponse);
        return boardListResponse;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BoardResponse fetchBoardById(@PathVariable Long id) {
        log.info("Fetching board with id {}... ", id);
        var board = boardService.getOneById(id);
        var boardResponse = boardMapper.toBoardResponse(board);
        log.info("Fetched board for id {} with body {}", id, boardResponse);
        return boardResponse;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public BoardResponse postBoard(@Validated @RequestBody AddBoardRequest addBoardRequest) {
        log.info("Creating board with body {}...", addBoardRequest);
        var board = boardMapper.toAddBoard(addBoardRequest);
        var savedBoard = boardService.createBoard(board);
        var boardResponse = boardMapper.toBoardResponse(savedBoard);
        log.info("Created board for id: {} with body {}  ...", savedBoard.getId(), boardResponse);
        return boardResponse;
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public BoardResponse putBoard(@PathVariable Long id, @Validated @RequestBody
    ModifyBoardRequest modifyBoardRequest) {
        log.info("Modifying board for id: {} with body {}...", id, modifyBoardRequest);
        var board = boardMapper.toModifyBoard(modifyBoardRequest);
        var updatedBoard = boardService.updateBoard(id, board);
        var boardResponse = boardMapper.toBoardResponse(updatedBoard);
        log.info("Modified board for id: {} with body {}...", id, boardResponse);
        return boardResponse;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeBoard(@PathVariable Long id) {
        log.info("Removing board with id: {}...", id);
        boardService.deleteBoard(id);
        log.info("Removed board for id: {}...", id);
    }
}
