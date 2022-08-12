package com.spring.deckofcards.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@RequiredArgsConstructor
@Table(name = "PLAYER")
@Entity
@Getter
@Setter
@AllArgsConstructor
@ToString
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Player implements Serializable {

    @Serial
    private static final long serialVersionUID = 4L;

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME")
    private String name;

    private int points;

    @JsonBackReference
    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "game_id")
    private Game game;

    @Override
    public boolean equals(Object p) {
        if (this == p) return true;
        if (p == null || Hibernate.getClass(this) != Hibernate.getClass(p)) return false;
        Player player = (Player) p;
        return id != null && Objects.equals(id, player.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }


}
