package hr.ericsson.sample.trello.controllers.request;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record ModifyCardListRequest(@NotNull String name) {
}
