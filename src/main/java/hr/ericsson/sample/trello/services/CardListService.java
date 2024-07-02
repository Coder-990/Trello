package hr.ericsson.sample.trello.services;

import hr.ericsson.sample.trello.repositories.models.CardList;

import java.util.List;

public interface CardListService {
    List<CardList> getAllCardList();

    CardList getCardListById(Long id);

    CardList createCardList(CardList card);

    CardList updateCardList(Long id, CardList cardList);

    void deleteCardList(Long id);
}
