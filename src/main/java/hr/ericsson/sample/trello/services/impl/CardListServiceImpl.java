package hr.ericsson.sample.trello.services.impl;

import hr.ericsson.sample.trello.repositories.CardListRepository;
import hr.ericsson.sample.trello.repositories.models.CardList;
import hr.ericsson.sample.trello.services.CardListService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CardListServiceImpl implements CardListService {

    private final CardListRepository cardListRepository;

    @Override
    public List<CardList> getAllCardList() {
        return cardListRepository.findAll();
    }

    @Override
    public CardList getCardListById(Long id) {
        return cardListRepository.getReferenceById(id);
    }

    @Override
    public CardList createCardList(CardList card) {
        return cardListRepository.save(card);
    }

     @Override
    public CardList updateCardList(Long id, CardList cardList) {
        var existingCard = getCardListById(id);
        existingCard.setName(cardList.getName());
        existingCard.setCards(cardList.getCards());
        existingCard.setBoard(cardList.getBoard());
        return cardListRepository.saveAndFlush(existingCard);
    }

    @Override
    public void deleteCardList(Long id) {
        cardListRepository.deleteById(id);
    }
}
