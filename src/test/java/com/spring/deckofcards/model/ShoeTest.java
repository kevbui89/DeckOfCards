package com.spring.deckofcards.model;

import com.spring.deckofcards.model.deck.Card;
import com.spring.deckofcards.model.deck.Suit;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import static org.junit.Assert.assertEquals;

public class ShoeTest {

    private Shoe shoe_;

    @Before
    public void setup() {
        shoe_ = new Shoe();
    }

    /**
     * 1st scenario: New Deck, all suits should return 13
     * 2nd scenario: Add Deck, all suits should return 26
     * 3rd scenario: Deal Card, all suits should return 26 except the suit of the card dealt.
     * @throws Exception
     */
    @Test
    public void givenScenarios_getSuitCount_ReturnCorrectSuitCount() throws Exception {
        assertEquals(13, Shoe.getSuitCount().get(Suit.CLUBS).intValue());
        assertEquals(13, Shoe.getSuitCount().get(Suit.DIAMONDS).intValue());
        assertEquals(13, Shoe.getSuitCount().get(Suit.HEARTS).intValue());
        assertEquals(13, Shoe.getSuitCount().get(Suit.SPADES).intValue());

        shoe_.addDeck();

        assertEquals(26, Shoe.getSuitCount().get(Suit.CLUBS).intValue());
        assertEquals(26, Shoe.getSuitCount().get(Suit.DIAMONDS).intValue());
        assertEquals(26, Shoe.getSuitCount().get(Suit.HEARTS).intValue());
        assertEquals(26, Shoe.getSuitCount().get(Suit.SPADES).intValue());

        Map<Integer, Stack<Card>> hands = new HashMap<>();
        hands.put(0, new Stack<>());
        Card card = shoe_.deal(0L, hands);

        assert card != null;
        if (card.getSuit().equals(Suit.CLUBS)) {
            assertEquals(25,Shoe.getSuitCount().get(Suit.CLUBS).intValue());
            assertEquals(26,Shoe.getSuitCount().get(Suit.SPADES).intValue());
            assertEquals(26,Shoe.getSuitCount().get(Suit.DIAMONDS).intValue());
            assertEquals(26,Shoe.getSuitCount().get(Suit.HEARTS).intValue());
        }

        if (card.getSuit().equals(Suit.SPADES)) {
            assertEquals(26,Shoe.getSuitCount().get(Suit.CLUBS).intValue());
            assertEquals(25,Shoe.getSuitCount().get(Suit.SPADES).intValue());
            assertEquals(26,Shoe.getSuitCount().get(Suit.DIAMONDS).intValue());
            assertEquals(26,Shoe.getSuitCount().get(Suit.HEARTS).intValue());
        }

        if (card.getSuit().equals(Suit.HEARTS)) {
            assertEquals(26,Shoe.getSuitCount().get(Suit.CLUBS).intValue());
            assertEquals(26,Shoe.getSuitCount().get(Suit.SPADES).intValue());
            assertEquals(26,Shoe.getSuitCount().get(Suit.DIAMONDS).intValue());
            assertEquals(25,Shoe.getSuitCount().get(Suit.HEARTS).intValue());
        }

        if (card.getSuit().equals(Suit.DIAMONDS)) {
            assertEquals(26,Shoe.getSuitCount().get(Suit.CLUBS).intValue());
            assertEquals(26,Shoe.getSuitCount().get(Suit.SPADES).intValue());
            assertEquals(25,Shoe.getSuitCount().get(Suit.DIAMONDS).intValue());
            assertEquals(26,Shoe.getSuitCount().get(Suit.HEARTS).intValue());
        }
    }
}
