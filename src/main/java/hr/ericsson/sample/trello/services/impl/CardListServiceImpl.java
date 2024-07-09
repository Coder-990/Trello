package hr.ericsson.sample.trello.services.impl;

import hr.ericsson.sample.trello.repositories.CardListRepository;
import hr.ericsson.sample.trello.repositories.models.CardList;
import hr.ericsson.sample.trello.services.CardListService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CardListServiceImpl implements CardListService {

    private final CardListRepository cardListRepository;

    @Override
    public List<CardList> getAllCardList() {
        return cardListRepository.findAll();
    }

    @Override
    public Optional<CardList> getCardListById(Long id) {
        return cardListRepository.findById(id);
    }

    @Override
    public CardList createCardList(CardList cardList) {
        return cardListRepository.save(cardList);
    }

     @Override
    public CardList updateCardList(Long id, CardList cardList) {
         return getCardListById(id)
                 .map(existingCardList -> {
                     existingCardList.setName(cardList.getName());
                     existingCardList.setCards(cardList.getCards());
                     existingCardList.setBoards(cardList.getBoards());
                     return cardListRepository.save(existingCardList);
                 }).orElseThrow(() -> new RuntimeException("CardList not found"));
    }

    @Override
    public void deleteCardList(Long id) {
        cardListRepository.deleteById(id);
    }
}
