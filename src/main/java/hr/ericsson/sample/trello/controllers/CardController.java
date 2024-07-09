package hr.ericsson.sample.trello.controllers;

import hr.ericsson.sample.trello.controllers.request.AddCardRequest;
import hr.ericsson.sample.trello.controllers.request.ModifyCardRequest;
import hr.ericsson.sample.trello.controllers.response.CardResponse;
import hr.ericsson.sample.trello.mappers.CardMapper;
import hr.ericsson.sample.trello.services.CardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
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
    @ResponseStatus(HttpStatus.OK)
    public List<CardResponse> fetchAllCards() {
        log.info("Fetching all cards from sample trello ...");
        var cards = cardService.getAllCards();
        var cardResponse = cardMapper.toCardListResponse(cards);
        log.info("Fetched all cards with body {}...", cardResponse);
        return cardResponse;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CardResponse fetchCardById(@PathVariable Long id) {
        log.info("Fetching card with id {}... ", id);
        var card = cardService.getCardById(id).get();
        var cardResponse = cardMapper.toCardResponse(card);
        log.info("Fetched card for id {} with body {}", id, cardResponse);
        return cardResponse;
    }

    @PostMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public CardResponse postCard(@PathVariable Long id, @Validated @RequestBody AddCardRequest addCardRequest) {
        log.info("Creating card with body {}...", addCardRequest);
        var card = cardMapper.toAddCard(addCardRequest);
        var savedCard = cardService.createCard(card, id);
        var cardResponse = cardMapper.toCardResponse(savedCard);
        log.info("Created card for id: {} with body {}  ...", savedCard.getId(), cardResponse);
        return cardResponse;
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public CardResponse putCard(@PathVariable Long id, @Validated @RequestBody
    ModifyCardRequest modifyCardRequest) {
        log.info("Modifying card for id: {} with body {}...", id, modifyCardRequest);
        var card = cardMapper.toModifyCard(modifyCardRequest);
        var updatedCard = cardService.updateCard(id, card);
        var cardResponse = cardMapper.toCardResponse(updatedCard);
        log.info("Modified card for id: {} with body {}...", id, cardResponse);
        return cardResponse;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeCard(@PathVariable Long id) {
        log.info("Removing card with id: {}...", id);
        cardService.deleteCard(id);
        log.info("Removed card for id: {}...", id);
    }
}
