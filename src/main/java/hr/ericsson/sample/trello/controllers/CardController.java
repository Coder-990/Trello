package hr.ericsson.sample.trello.controllers;

import hr.ericsson.sample.trello.mappers.CardMapper;
import hr.ericsson.sample.trello.repositories.models.Card;
import hr.ericsson.sample.trello.services.CardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/cards")
public class CardController {

    private final CardService cardService;
    private final CardMapper cardMapper;

    @GetMapping
    public List<Card> getAllCards() {
        return cardService.getAllCards();
    }

    @GetMapping("/v1/cards/{id}")
    public Card getProductById(@PathVariable Long id) {
        log.info("Fetching card with id {}... ", id);
        var card = cardService.getCardById(id);
//        var cardResponse = cardMapper.toCardResponse(card);
        log.info("Fetched product with id {}", id);
//        return cardResponse;
   return null;
    }

    @PostMapping
    public Card createCard(@RequestBody Card card) {
        return cardService.createCard(card);
    }
}
