package hr.ericsson.sample.trello.services;

import hr.ericsson.sample.trello.repositories.models.Card;

import java.util.List;

public interface CardService {
    List<Card> getAllCards();

    Card getCardById(Long id);

    Card createCard(Card card);

    Card updateCard(Long id, Card card);

    void deleteCard(Long id);
}
