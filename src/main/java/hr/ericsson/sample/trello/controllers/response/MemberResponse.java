package hr.ericsson.sample.trello.controllers.response;

import jakarta.validation.constraints.NotNull;

public record MemberResponse(
        @NotNull String name,
        @NotNull String lastName,
        @NotNull String email,
        @NotNull String phone
) {
}
