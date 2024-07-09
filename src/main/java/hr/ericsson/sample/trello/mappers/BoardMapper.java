package hr.ericsson.sample.trello.mappers;

import hr.ericsson.sample.trello.controllers.request.AddBoardRequest;
import hr.ericsson.sample.trello.controllers.request.ModifyBoardRequest;
import hr.ericsson.sample.trello.controllers.response.BoardResponse;
import hr.ericsson.sample.trello.repositories.models.Board;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface BoardMapper {

    @Mapping(target = "id", ignore = true)
    Board toAddBoard(AddBoardRequest addBoardRequest);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "cardLists", ignore = true)
    Board toModifyBoard(ModifyBoardRequest modifyCardListRequest);

    BoardResponse toBoardResponse(Board board);

    List<BoardResponse> toBoardListResponse(List<Board> boards);
}
