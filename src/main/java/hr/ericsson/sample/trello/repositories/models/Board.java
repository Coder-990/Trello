package hr.ericsson.sample.trello.repositories.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import lombok.*;

import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @JsonManagedReference
    @ToString.Exclude
    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(
            name = "board_card_list",
            joinColumns = @JoinColumn(name = "board_id"),
            inverseJoinColumns = @JoinColumn(name = "card_list_id")
    )
    private List<CardList> cardLists;
}
