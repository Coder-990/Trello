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
    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(
            name = "card_member",
            joinColumns = @JoinColumn(name = "card_id"),
            inverseJoinColumns = @JoinColumn(name = "member_id")
    )
    private List<Member> members;


    @JsonBackReference
    @ManyToMany(mappedBy = "cards", cascade = CascadeType.ALL)
    private List<CardList> cardLists;
}
