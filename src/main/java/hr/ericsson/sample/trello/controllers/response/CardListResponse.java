package hr.ericsson.sample.trello.controllers.response;

import hr.ericsson.sample.trello.repositories.models.Card;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.util.List;

@Builder
public record CardListResponse(@NotNull String name, List<Card> cards) {
}
