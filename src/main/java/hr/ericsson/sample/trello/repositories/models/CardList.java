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

    @OneToMany(mappedBy = "cardList", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Card> cards;

    @ManyToOne
    @JoinColumn(name = "BOARD_ID")
    private Board board;
}
