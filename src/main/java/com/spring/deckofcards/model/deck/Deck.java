package com.spring.deckofcards.model.deck;

import com.spring.deckofcards.util.DeckUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Optional;
import java.util.Stack;

@Builder
@AllArgsConstructor
@Getter
public class Deck {

    private Stack<Card> deck;

    public Deck(boolean shuffle) {
        this.deck = init(shuffle);
    }

    /**
     * Initializes the deck
     *
     * @return Deck of cards
     */
    private Stack<Card> init(boolean shuffle) {
        Stack<Card> deck = new Stack<>();
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                deck.push(Card.getCard(rank, suit));
            }
        }

        if (shuffle) {
            return DeckUtil.shuffle(deck);
        }
        return deck;
    }

    public int size() {
        return deck.size();
    }

    public boolean contains(Card card) {
        return this.deck.contains(card);
    }
}
