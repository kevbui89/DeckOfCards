package com.spring.deckofcards.model.deck;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(Parameterized.class)
public class CardTest {

    private final Card card1;
    private final Card card2;
    private final int expectedResult;

    public CardTest(Card card1, Card card2, int expectedResult) {
        this.card1 = card1;
        this.card2 = card2;
        this.expectedResult = expectedResult;
    }

    @Parameterized.Parameters
    public static Collection input() {
        return Arrays.asList(new Object[][]{
                {new Card(Rank.TWO, Suit.CLUBS), new Card(Rank.TWO, Suit.CLUBS), 0},
                {new Card(Rank.TWO, Suit.DIAMONDS), new Card(Rank.TWO, Suit.CLUBS), 1}
        });
    }

    @Test
    public void givenTwoCards_CompareTo_ReturnCorrectIntResult() throws Exception {
        System.out.println("First card: " + card1.getSuit() + " of " + card1.getRank());
        System.out.println("Second card: " + card2.getSuit() + " of " + card2.getRank());
        System.out.println("Expected Result: " + expectedResult);
        System.out.println("Actual Result: " + card1.compareTo(card2));
        System.out.println("===============");
        assertEquals(expectedResult, card1.compareTo(card2));
    }

}
