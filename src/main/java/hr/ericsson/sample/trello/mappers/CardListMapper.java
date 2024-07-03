package hr.ericsson.sample.trello.mappers;

import hr.ericsson.sample.trello.controllers.request.AddCardListRequest;
import hr.ericsson.sample.trello.controllers.request.ModifyCardListRequest;
import hr.ericsson.sample.trello.controllers.response.CardListResponse;
import hr.ericsson.sample.trello.repositories.models.CardList;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CardListMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "board", ignore = true)
    CardList toAddCardList(AddCardListRequest addCardListRequest);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "cards", ignore = true)
    @Mapping(target = "board", ignore = true)
    CardList toModifyCardList(ModifyCardListRequest modifyCardListRequest);

    CardListResponse toCardListResponse(CardList cardList);

    List<CardListResponse> toListCardListResponse(List<CardList> cardList);
}
