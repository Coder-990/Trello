package hr.ericsson.sample.trello.services.impl;

import hr.ericsson.sample.trello.repositories.CardRepository;
import hr.ericsson.sample.trello.repositories.models.Card;
import hr.ericsson.sample.trello.services.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {

    private final CardRepository cardRepository;

    @Override
    public List<Card> getAllCards() {
        return cardRepository.findAll();
    }

    @Override
    public Card getCardById(Long id) {
        return cardRepository.getReferenceById(id);
    }

    @Override
    public Card createCard(Card card) {
        return cardRepository.save(card);
    }

    @Override
    public Card updateCard(Card card) {
        var existingCard = getCardById(card.getId());
        existingCard.setText(card.getText());
        existingCard.setCardList(card.getCardList());
        return cardRepository.saveAndFlush(existingCard);
    }

    @Override
    public void deleteCard(Long id) {
        cardRepository.deleteById(id);
    }
}