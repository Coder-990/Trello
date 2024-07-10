package hr.ericsson.sample.trello.controllers.request;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.util.List;

@Builder
public record ModifyCardRequest(
        @NotNull String name,
        @NotNull String describe,
        @NotNull List<ModifyMemberRequest> members) {
}
