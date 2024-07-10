package hr.ericsson.sample.trello.repositories.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CardList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(
            name = "card_list_card",
            joinColumns = @JoinColumn(name = "card_list_id"),
            inverseJoinColumns = @JoinColumn(name = "card_id")
    )
    private List<Card> cards;

    @ManyToMany(mappedBy = "cardLists", cascade = CascadeType.ALL)
    private List<Board> boards;
}
