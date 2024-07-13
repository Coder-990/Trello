package hr.ericsson.sample.trello.controllers;

import hr.ericsson.sample.trello.TestBase;
import hr.ericsson.sample.trello.controllers.response.BoardsResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class BoardControllerTest extends TestBase {

    @Test
    @DisplayName("""
            Given empty list of boards,
             when fetching all boards,
             then is expected to return empty list with status code Ok
            """)
    void shouldReturnEmptyListOfBoardsWithStatusCodeOK() throws Exception {
        //given & then
        var result = mockMvc.perform(get("/v1/boards"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse()
                .getContentAsString();
        //then
        var boardsResponse = objectMapper.readValue(result, BoardsResponse.class);
        assertThat(boardsResponse.boards()).isEmpty();
    }
}
