package hr.ericsson.sample.trello.services.impl;

import hr.ericsson.sample.trello.repositories.CardRepository;
import hr.ericsson.sample.trello.repositories.models.Card;
import hr.ericsson.sample.trello.services.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {

    private final CardRepository cardRepository;

    @Override
    public List<Card> getAllCards() {
        return cardRepository.findAll();
    }

    @Override
    public Optional<Card> getCardById(Long id) {
        return cardRepository.findById(id);
    }

    @Override
    public Card createCard(Card card) {
        return cardRepository.save(card);
    }

    @Override
    public Card updateCard(Long id, Card card) {
        return getCardById(id)
                .map(existingCard -> {
                    existingCard.setName(card.getName());
                    existingCard.setDescribe(card.getDescribe());
                    existingCard.setMembers(card.getMembers());
                    existingCard.setCardLists(card.getCardLists());
                    return cardRepository.save(existingCard);
                }).orElseThrow(() -> new RuntimeException("Card not found"));
    }

    @Override
    public void deleteCard(Long id) {
        cardRepository.deleteById(id);
    }
}
