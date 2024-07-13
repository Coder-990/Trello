package hr.ericsson.sample.trello.controllers.response;

import lombok.Builder;

import java.util.List;

@Builder
public record MembersResponse(List<MemberResponse> members) {
}
