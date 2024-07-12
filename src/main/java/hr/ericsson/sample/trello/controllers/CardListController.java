package hr.ericsson.sample.trello.controllers;

import hr.ericsson.sample.trello.controllers.request.AddCardListRequest;
import hr.ericsson.sample.trello.controllers.request.ModifyCardListRequest;
import hr.ericsson.sample.trello.controllers.response.CardListResponse;
import hr.ericsson.sample.trello.exceptions.NotFoundException;
import hr.ericsson.sample.trello.mappers.CardListMapper;
import hr.ericsson.sample.trello.services.CardListService;
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
@RequestMapping("/v1/card-list")
@CrossOrigin(origins = "http://localhost:4200")
public class CardListController {

    private final CardListService cardListService;
    private final CardListMapper cardListMapper;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CardListResponse> fetchAllCardList() {
        log.info("Fetching all card list from sample trello ...");
        var cardList = cardListService.getAllCardList();
        var cardListResponse = cardListMapper.toListCardListResponse(cardList);
        log.info("Fetched all card lists with body {}...", cardListResponse);
        return cardListResponse;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CardListResponse fetchCardListById(@PathVariable Long id) {
        log.info("Fetching card list with id {}... ", id);
        var cardList = cardListService.getCardListById(id);
        var cardListResponse = cardList.map(cardListMapper::toCardListResponse)
                .orElseThrow(() -> new NotFoundException(("Could not find card list by this id %s").formatted(id)));
        log.info("Fetched card list for id {} with body {}", id, cardListResponse);
        return cardListResponse;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public CardListResponse postCardList(@Validated @RequestBody AddCardListRequest addCardListRequest) {
        log.info("Creating card with body {}...", addCardListRequest);
        var cardList = cardListMapper.toAddCardList(addCardListRequest);
        var savedCardList = cardListService.createCardList(cardList);
        var cardListResponse = cardListMapper.toCardListResponse(savedCardList);
        log.info("Created card for id: {} with body {}  ...", savedCardList.getId(), cardListResponse);
        return cardListResponse;
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public CardListResponse putCardList(@PathVariable Long id, @Validated @RequestBody
    ModifyCardListRequest modifyCardListRequest) {
        log.info("Modifying card list for id: {} with body {}...", id, modifyCardListRequest);
        var cardList = cardListMapper.toModifyCardList(modifyCardListRequest);
        var updatedCardList = cardListService.updateCardList(id, cardList);
        var cardListResponse = cardListMapper.toCardListResponse(updatedCardList);
        log.info("Modified card list for id: {} with body {}...", id, cardListResponse);
        return cardListResponse;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeCardList(@PathVariable Long id) {
        log.info("Removing card list with id: {}...", id);
        cardListService.deleteCardList(id);
        log.info("Removed card list for id: {}...", id);
    }
}
