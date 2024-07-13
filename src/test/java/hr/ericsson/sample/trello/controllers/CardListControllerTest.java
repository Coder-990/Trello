package hr.ericsson.sample.trello.controllers;

import hr.ericsson.sample.trello.TestBase;
import hr.ericsson.sample.trello.controllers.response.CardListsResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CardListControllerTest extends TestBase {

    @Test
    @DisplayName("""
            Given empty list of cardLists,
             when fetching all cardLists,
             then is expected to return empty list with status code Ok
            """)
    void shouldReturnEmptyListOfCardListsWithStatusCodeOK() throws Exception {
        //given & then
        var result = mockMvc.perform(get("/v1/card-lists"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse()
                .getContentAsString();
        //then
        var cardListsResponse = objectMapper.readValue(result, CardListsResponse.class);
        assertThat(cardListsResponse.cardLists()).isEmpty();
    }
}
