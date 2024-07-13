package hr.ericsson.sample.trello.controllers;

import hr.ericsson.sample.trello.TestBase;
import hr.ericsson.sample.trello.controllers.response.MemberResponse;
import hr.ericsson.sample.trello.controllers.response.MembersResponse;
import hr.ericsson.sample.trello.fixtures.MembersFixture;
import hr.ericsson.sample.trello.repositories.models.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class MemberControllerTest extends TestBase {

    @Test
    @DisplayName("""
            Given empty list of members,
             when fetching all members,
             then is expected to return empty list with status code Ok
            """)
    void shouldReturnEmptyListOfMembersWithStatusCodeOK() throws Exception {
        //given & then
        var result = mockMvc.perform(get("/v1/members"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse()
                .getContentAsString();
        //then
        var membersResponse = objectMapper.readValue(result, MembersResponse.class);
        assertThat(membersResponse.members()).isEmpty();
    }

    @Test
    @DisplayName("""
            Given multiple members to database,
             when fetching all members,
             then is expected to return all members with status code Ok
            """)
    void shouldReturnMembersWithStatusCodeOK() throws Exception {
        //given
        memberRepository.save(MembersFixture.getMember().build());
        memberRepository.save(MembersFixture.getMember()
                .id(2L)
                .name("Doe")
                .lastName("John")
                .email("doe.john@email.com")
                .phone("412587747")
                .build());
        //when
        var result = mockMvc.perform(get("/v1/members"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse()
                .getContentAsString();
        //then
        var membersResponse = objectMapper.readValue(result, MembersResponse.class);
        assertThat(membersResponse.members()).hasSize(2);

        var first = membersResponse.members().getFirst();
        var last = membersResponse.members().getLast();

        assertThat(first.name()).isEqualTo("John");
        assertThat(first.lastName()).isEqualTo("Doe");
        assertThat(first.email()).isEqualTo("john.doe@example.com");
        assertThat(first.phone()).isEqualTo("125652547");
        assertThat(last.name()).isEqualTo("Doe");
        assertThat(last.lastName()).isEqualTo("John");
        assertThat(last.email()).isEqualTo("doe.john@email.com");
        assertThat(last.phone()).isEqualTo("412587747");
    }

    @Test
    @DisplayName("""
            Given multiple members to database,
             when fetching member by particular id,
             then is expected to return member by this particular id with status code Ok
            """)
    void shouldReturnMemberByIdWithStatusCodeOK() throws Exception {
        //given
        memberRepository.save(MembersFixture.getMember().build());
        memberRepository.save(MembersFixture.getMember()
                .id(2L)
                .name("Doe")
                .lastName("John")
                .email("doe.john@email.com")
                .phone("412587747")
                .build());
        //when
        var firstId = memberRepository.findAll().stream().map(Member::getId).findFirst().orElseThrow();
        var result = mockMvc.perform(get("/v1/members/" + firstId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse()
                .getContentAsString();
        //then
        var membersResponse = objectMapper.readValue(result, MemberResponse.class);
        assertThat(membersResponse.name()).isEqualTo("John");
        assertThat(membersResponse.lastName()).isEqualTo("Doe");
        assertThat(membersResponse.email()).isEqualTo("john.doe@example.com");
        assertThat(membersResponse.phone()).isEqualTo("125652547");
    }
}
