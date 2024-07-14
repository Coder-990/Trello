package hr.ericsson.sample.trello.fixtures;

import hr.ericsson.sample.trello.controllers.request.AddMemberRequest;
import hr.ericsson.sample.trello.repositories.models.Member;

public class MembersFixture {

    private MembersFixture() {}

    public static Member.MemberBuilder getMember() {
        return Member.builder()
                .name("John")
                .lastName("Doe")
                .email("john.doe@example.com")
                .phone("125652547");
    }

    public static AddMemberRequest.AddMemberRequestBuilder addMemberRequestBuilder(){
        return AddMemberRequest.builder()
                .name("John")
                .lastName("Doe")
                .email("john.doe@example.com")
                .phone("125652547");
    }
}
