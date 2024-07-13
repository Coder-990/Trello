package hr.ericsson.sample.trello.controllers;

import hr.ericsson.sample.trello.TestBase;
import hr.ericsson.sample.trello.controllers.response.CardsResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CardControllerTest extends TestBase {

    @Test
    @DisplayName("""
            Given empty list of cards,
             when fetching all cards,
             then is expected to return empty list with status code Ok
            """)
    void shouldReturnEmptyListOfCardsWithStatusCodeOK() throws Exception {
        //given & then
        var result = mockMvc.perform(get("/v1/cards"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse()
                .getContentAsString();
        //then
        var cardsResponse = objectMapper.readValue(result, CardsResponse.class);
        assertThat(cardsResponse.cards()).isEmpty();
    }
}
