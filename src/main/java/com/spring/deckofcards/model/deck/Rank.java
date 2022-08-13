package com.spring.deckofcards.model.deck;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * No setter because a card value is final and cannot be changed.
 */
@Getter
@AllArgsConstructor
public enum Rank {

    KING(13),
    QUEEN(12),
    JACK(11),
    TEN(10),
    NINE(9),
    EIGHT(8),
    SEVEN(7),
    SIX(6),
    FIVE(5),
    FOUR(4),
    THREE(3),
    TWO(2),
    ACE(1);

    private final int value;
}
