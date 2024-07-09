package hr.ericsson.sample.trello.services;

import hr.ericsson.sample.trello.repositories.models.CardList;

import java.util.List;
import java.util.Optional;

public interface CardListService {
    List<CardList> getAllCardList();

    Optional<CardList> getCardListById(Long id);

    CardList createCardList(CardList card);

    CardList updateCardList(Long id, CardList cardList);

    void deleteCardList(Long id);
}
