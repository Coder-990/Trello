package hr.ericsson.sample.trello.controllers.request;

import lombok.Builder;

@Builder
public record ModifyMemberRequest(String name, String lastName, String email, String phone) {
}
