package com.spring.deckofcards.model.deck;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Builder
@AllArgsConstructor
@Getter
public class Card implements Comparable<Card> {

    private final static Map<String, Card> CARD_CACHE = initCache();
    private final Rank rank;
    private final Suit suit;

    /**
     * Contains all 52 possible cards of the deck
     * Ensures that any returned card is one of the possible 52
     * @return A key/pair value map of the 52 card keys and the card objects
     */
    private static Map<String, Card> initCache() {
        final Map<String, Card> cache = new HashMap<>();
        for (final Suit suit : Suit.values()) {
            for (final Rank rank : Rank.values()) {
                cache.put(cardKey(rank, suit), new Card(rank, suit));
            }
        }
        return Collections.unmodifiableMap(cache);
    }

    /**
     * Returns a validated (non-null) card from the map.
     * @param rank The rank of the card
     * @param suit The suit of the card
     * @return The card from the cache
     */
    static Card getCard(final Rank rank, final Suit suit) {
        final Card card = CARD_CACHE.get(cardKey(rank, suit));
        if (card != null) {
            return card;
        }
        throw new RuntimeException(rank + " of " + suit + " does not exist.");
    }

    /**
     * Returns a card key with the rank and suit as a String
     * @param rank The rank of the card
     * @param suit The suit of the card
     * @return The key/value pair of the card
     */
    private static String cardKey(final Rank rank, final Suit suit) {
        return rank + " of " + suit;
    }

    @Override
    public int compareTo(Card o) {
        return (Integer.compare(this.rank.getValue(), o.rank.getValue()) == 0
                && Integer.compare(this.suit.getValue(), o.suit.getValue()) == 0) ? 0 : 1;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }

        if (o.getClass() != this.getClass()) {
            return false;
        }

        final Card other = (Card) o;
        if (!Objects.equals(this.rank, other.rank)) {
            return false;
        }

        return Objects.equals(this.suit, other.suit);
    }

    @Override
    public String toString() {
        return this.rank + " of " + this.suit;
    }
}
