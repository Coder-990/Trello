package hr.ericsson.sample.trello.controllers;

import hr.ericsson.sample.trello.TestBase;
import hr.ericsson.sample.trello.controllers.response.MemberResponse;
import hr.ericsson.sample.trello.controllers.response.MembersResponse;
import hr.ericsson.sample.trello.fixtures.MembersFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.http.ProblemDetail;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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
        var id = memberRepository.save(MembersFixture.getMember().build()).getId();
        memberRepository.save(MembersFixture.getMember()
                .name("Doe")
                .lastName("John")
                .email("doe.john@email.com")
                .phone("412587747")
                .build());
        //when
        var result = mockMvc.perform(get("/v1/members/" + id))
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

    @Test
    @DisplayName("""
            Given multiple members to database,
             when fetching member by particular non existing id,
             then is expected to return Not found exception with status code Not Found
            """)
    void shouldReturnNotFoundExceptionByNonExistingIdWithStatusCodeNotFound() throws Exception {
        //given
        var memberId = memberRepository.save(MembersFixture.getMember().build()).getId();
        memberRepository.save(MembersFixture.getMember()
                .name("Doe")
                .lastName("John")
                .email("doe.john@email.com")
                .phone("412587747")
                .build());
        var nonExistingId = memberId + 10;
        //when
        var result = mockMvc.perform(get("/v1/members/" + nonExistingId))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_PROBLEM_JSON))
                .andReturn()
                .getResponse()
                .getContentAsString();
        //then
        var membersResponse = objectMapper.readValue(result, ProblemDetail.class);
        assertThat(membersResponse.getType()).hasToString("about:blank");
        assertThat(membersResponse.getTitle()).isEqualTo("Not Found");
        assertThat(membersResponse.getStatus()).isEqualTo(404);
        assertThat(membersResponse.getDetail())
                .contains("Could not find member by this id " + nonExistingId);
        assertThat(membersResponse.getInstance()).hasToString("/v1/members/" + nonExistingId);
    }

    @Test
    @DisplayName("""
            Given new member request does not exist in database,
             when new member request is sent, validated and all passed well
             then new member is created and expected to return status code created
            """)
    void shouldReturnNewCreatedMemberWithStatusCodeCreated() throws Exception {
        //given
        var addMemberRequest = MembersFixture.addMemberRequestBuilder().build();
        //when
        var result = mockMvc.perform(post("/v1/members")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(addMemberRequest)))
                .andExpect(status().isCreated())
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

    @Test
    @DisplayName("""
            Given new member request does not exist in database,
             when new member request is sent, validated and all passed well
             then new member is created and expected to return status code created
            """)
    void shouldReturnValidationErrorWithCodeBadRequest() throws Exception {
        //given
        var addMemberRequest = MembersFixture.addMemberRequestBuilder()
                .name("")
                .lastName("a".repeat(26))
                .email("e".repeat(131))
                .phone(null)
                .build();
        //when
        var result = mockMvc.perform(post("/v1/members")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(addMemberRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_PROBLEM_JSON))
                .andReturn()
                .getResponse()
                .getContentAsString();
        //then
        var membersResponse = objectMapper.readValue(result, ProblemDetail.class);
        assertThat(membersResponse.getType()).hasToString("about:blank");
        assertThat(membersResponse.getTitle()).isEqualTo("Bad Request");
        assertThat(membersResponse.getStatus()).isEqualTo(400);
        assertThat(membersResponse.getDetail())
                .contains("name: size must be between 3 and 25")
                .contains("name: must not be blank")
                .contains("lastName: size must be between 3 and 25")
                .contains("email: size must be between 0 and 130")
                .contains("phone: must not be blank")
                .contains("phone: must not be null");
        assertThat(membersResponse.getInstance()).hasToString("/v1/members");
        assertThat(memberRepository.count()).isZero();
    }

    @Test
    @DisplayName("""
            Given member exists in database by id
             when fetching member by requested id to remove
             then is expected to remove that member and return status no content
            """)
    void shouldReturnStatusNoContentWhenSuccessfullyRemovedMember() throws Exception {
        // given
        var memberId = memberRepository.save(MembersFixture.getMember().build()).getId();
        // when
        mockMvc.perform(delete("/v1/members/" + memberId))
                .andExpect(status().isNoContent());
        // then
        assertThat(memberRepository.count()).isZero();
    }

    @Test
    @DisplayName("""
            Given member exists in database by id
             when fetching member by requested non existing id to remove
             then is expected to return not found exception and return status not found
            """)
    void shouldReturnStatusNotFoundWhenExceptionIsThrownByNonExistingMemberID() throws Exception {
        // given
        var memberId = memberRepository.save(MembersFixture.getMember().build()).getId();
        var nonExistingId = memberId + 10;
        // when
        var result = mockMvc.perform(get("/v1/members/" + nonExistingId))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_PROBLEM_JSON))
                .andReturn()
                .getResponse()
                .getContentAsString();
        // then
        var membersResponse = objectMapper.readValue(result, ProblemDetail.class);
        assertThat(membersResponse.getType()).hasToString("about:blank");
        assertThat(membersResponse.getTitle()).isEqualTo("Not Found");
        assertThat(membersResponse.getStatus()).isEqualTo(404);
        assertThat(membersResponse.getDetail()).isEqualTo("Could not find member by this id "+ nonExistingId);
        assertThat(membersResponse.getInstance()).hasToString("/v1/members/" + nonExistingId);
    }
}
