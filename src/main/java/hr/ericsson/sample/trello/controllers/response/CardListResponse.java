package hr.ericsson.sample.trello.controllers.response;

import hr.ericsson.sample.trello.repositories.models.Board;
import hr.ericsson.sample.trello.repositories.models.CardList;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.util.List;

@Builder
public record CardListResponse(@NotNull String name, List<CardList> cards, Board board) {
}
