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
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String describe;

    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(
            name = "card_member",
            joinColumns = @JoinColumn(name = "card_id"),
            inverseJoinColumns = @JoinColumn(name = "member_id")
    )
    private List<Member> members;

    @ManyToMany(mappedBy = "cards", cascade = CascadeType.ALL)
    private List<CardList> cardLists;
}
