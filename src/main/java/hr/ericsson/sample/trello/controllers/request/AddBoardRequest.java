package hr.ericsson.sample.trello.controllers.request;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.util.List;

@Builder
public record AddBoardRequest(@NotNull String name, List<CardList> cardLists) {

    @Builder
    public record CardList(String name, List<Card> cards) {

        @Builder
        public record Card(String name, String describe, List<Member> members) {

            @Builder
            public record Member(String name, String lastName, String email, String phone) {
            }
        }
    }
}
