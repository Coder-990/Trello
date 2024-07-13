package hr.ericsson.sample.trello;

import com.fasterxml.jackson.databind.ObjectMapper;
import hr.ericsson.sample.trello.repositories.BoardRepository;
import hr.ericsson.sample.trello.repositories.CardListRepository;
import hr.ericsson.sample.trello.repositories.CardRepository;
import hr.ericsson.sample.trello.repositories.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@Import(TrelloApplication.class)
public abstract class TestBase {

    @Autowired protected ObjectMapper objectMapper;

    @Autowired protected MockMvc mockMvc;

    @Autowired protected MemberRepository memberRepository;

    @Autowired protected CardRepository cardRepository;

    @Autowired protected CardListRepository cardListRepository;

    @Autowired protected BoardRepository boardRepository;

    @BeforeEach
    protected void setUp(){
        memberRepository.deleteAll();
        cardRepository.deleteAll();
        boardRepository.deleteAll();
        cardListRepository.deleteAll();
    }
}
