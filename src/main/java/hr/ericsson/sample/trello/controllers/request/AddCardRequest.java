package hr.ericsson.sample.trello.controllers.request;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.util.List;

@Builder
public record AddCardRequest(@NotNull String name, @NotNull String describe, List<Member> members) {

    @Builder
    public record Member(String name, String lastName, String email, String phone) {
    }
}
