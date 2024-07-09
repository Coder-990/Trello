package hr.ericsson.sample.trello.controllers.request;

import hr.ericsson.sample.trello.controllers.response.CardResponse;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.util.List;

@Builder
public record AddCardListRequest(
        @NotNull String name,
        List<CardResponse> cards
) {
}
