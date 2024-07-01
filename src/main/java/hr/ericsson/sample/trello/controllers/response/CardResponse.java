package hr.ericsson.sample.trello.controllers.response;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record CardResponse(@NotNull String text) {
}
