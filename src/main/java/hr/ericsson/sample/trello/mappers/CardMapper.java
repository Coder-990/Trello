package hr.ericsson.sample.trello.mappers;

import hr.ericsson.sample.trello.controllers.request.AddCardRequest;
import hr.ericsson.sample.trello.controllers.request.ModifyCardRequest;
import hr.ericsson.sample.trello.controllers.response.CardResponse;
import hr.ericsson.sample.trello.repositories.models.Card;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CardMapper {


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "cardLists", ignore = true)
    Card toAddCard(AddCardRequest addCardRequest);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "cardLists", ignore = true)
    Card toModifyCard(ModifyCardRequest modifyCardRequest);

    CardResponse toCardResponse(Card card);

    List<CardResponse> toCardListResponse(List<Card> cards);
}
