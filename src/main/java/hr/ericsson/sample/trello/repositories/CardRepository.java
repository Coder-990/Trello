package hr.ericsson.sample.trello.repositories;

import hr.ericsson.sample.trello.repositories.models.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Long> {
}
