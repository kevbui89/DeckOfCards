package com.spring.deckofcards.model.deck;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * No setter as suit is final and cannot be changed.
 */
@Getter
@AllArgsConstructor
public enum Suit {

    DIAMONDS(1),
    HEARTS(2),
    SPADES(3),
    CLUBS(4);

    private final int value;
}
