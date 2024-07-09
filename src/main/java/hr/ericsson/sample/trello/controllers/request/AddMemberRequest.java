package hr.ericsson.sample.trello.controllers.request;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record AddMemberRequest(
        @NotNull String name,
        @NotNull String lastName,
        @NotNull String email,
        @NotNull String phone
) {}
