package com.spring.deckofcards.util;

import com.spring.deckofcards.model.deck.Card;

import java.util.Random;
import java.util.Stack;

public class DeckUtil {

    private DeckUtil() {
        // hide constructor
    }

    public static DeckUtil create() {
        return new DeckUtil();
    }

    /**
     * Shuffles a deck
     * @param deck
     * @return
     */
    public static Stack<Card> shuffle(Stack<Card> deck) {
        Random rand = new Random();

        for (int i = 0; i < deck.size(); i++) {
            int indexToSwap = rand.nextInt(deck.size());
            Card temp = deck.get(indexToSwap);
            deck.set(indexToSwap, deck.get(i));
            deck.set(i, temp);
        }
        return deck;
    }
}
