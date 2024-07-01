package hr.ericsson.sample.trello.controllers.request;

import lombok.Builder;

@Builder
public record CardRequest(
        String text
) {
}
