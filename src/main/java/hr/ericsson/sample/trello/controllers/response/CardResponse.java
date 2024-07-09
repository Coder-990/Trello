package hr.ericsson.sample.trello.controllers.response;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.util.List;

@Builder
public record CardResponse(
        @NotNull String name,
        String describe,
        List<MemberResponse> members) {
}
