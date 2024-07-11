package hr.ericsson.sample.trello.controllers.request;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record MoveCardRequest(@NotNull Long newCardListId) {
}