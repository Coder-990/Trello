package hr.ericsson.sample.trello.repositories.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

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

    @JsonManagedReference
    @ToString.Exclude
    @OneToMany(mappedBy = "cardList", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Card> cards;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "BOARD_ID")
    private Board board;
}
