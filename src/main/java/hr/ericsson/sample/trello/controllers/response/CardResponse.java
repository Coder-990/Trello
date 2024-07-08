package hr.ericsson.sample.trello.controllers.response;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.util.List;

@Builder
public record CardResponse(@NotNull String name, @NotNull String describe, List<Member> members) {

    @Builder
    public record Member(String name, String lastName, String email, String phone) {
    }
}
