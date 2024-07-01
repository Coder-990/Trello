package hr.ericsson.sample.trello.controllers.response;

import lombok.Builder;

@Builder
public record CardResponse(String text) {
}
