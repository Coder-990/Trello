package hr.ericsson.sample.trello.repositories;

import hr.ericsson.sample.trello.repositories.models.CardList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardListRepository extends JpaRepository<CardList, Long> {
}
