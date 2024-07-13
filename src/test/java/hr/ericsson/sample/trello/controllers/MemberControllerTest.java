package hr.ericsson.sample.trello.controllers;

import hr.ericsson.sample.trello.TestBase;
import hr.ericsson.sample.trello.controllers.response.MembersResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.assertj.core.api.Assertions.assertThat;

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
}
