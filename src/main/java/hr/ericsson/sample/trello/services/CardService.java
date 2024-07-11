package hr.ericsson.sample.trello.services;

import hr.ericsson.sample.trello.repositories.models.Card;

import java.util.List;
import java.util.Optional;

public interface CardService {
    List<Card> getAllCards();

    Optional<Card> getCardById(Long id);

    Card createCard(Card card);

    Card updateCard(Long id, Card card);

    Card moveCardToList(Long id, Long newCardListId);

    void deleteCard(Long id);
}
