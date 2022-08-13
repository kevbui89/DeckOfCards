package com.spring.deckofcards.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "GAME")
public class Game {

    @Id
    @Column(name = "ID", nullable = false)
    private Long id;

    private String name;

    @JsonManagedReference
    @OneToMany(mappedBy = "game", cascade =
            {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE} , fetch = FetchType.LAZY)
    private List<Player> players;

    public Game (String name) {
        this.name = name;
    }


}
