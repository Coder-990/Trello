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
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String describe;

    @JsonManagedReference
    @ToString.Exclude
    @OneToMany(mappedBy = "card", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Member> members;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "CARD_LIST_ID")
    private CardList cardList;
}
