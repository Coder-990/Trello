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
    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(
            name = "card_list_card",
            joinColumns = @JoinColumn(name = "card_list_id"),
            inverseJoinColumns = @JoinColumn(name = "card_id")
    )
    private List<Card> cards;

    @JsonBackReference
    @ManyToMany(mappedBy = "cardLists", cascade = CascadeType.ALL)
    private List<Board> boards;
}
