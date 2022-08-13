package com.spring.deckofcards.helper;

import com.spring.deckofcards.model.deck.Card;
import com.spring.deckofcards.model.deck.Deck;
import com.spring.deckofcards.model.deck.Rank;
import com.spring.deckofcards.model.deck.Suit;
import org.junit.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GameHelperTest {
    private final GameHelper helper_ = GameHelper.create();

    @Test
    public void givenHand_calculateScoreBoard_ReturnScorePerPlayer() throws Exception {
        HashMap<Integer, Stack<Card>> hands = new HashMap<>();
        Stack<Card> hand = new Stack<>();
        hand.push(new Card(Rank.ACE, Suit.HEARTS));
        hand.push(new Card(Rank.QUEEN, Suit.CLUBS));
        hand.push(new Card(Rank.KING, Suit.CLUBS));
        hands.put(0, hand);

        Map<Integer, Integer> testScore = helper_.calculateScoreBoard(hands);

        assertEquals(26, testScore.get(0));
    }

    @Test
    public void givenListOfPlayers_sortPlayersByScoreDescending_returnHighestScorePlayer() throws Exception {
        HashMap<Integer, Integer> scoreBoard = new HashMap<Integer, Integer>();
        scoreBoard.put(1, 10);
        scoreBoard.put(2, 23);
        scoreBoard.put(3, 2);
        scoreBoard.put(4, 43);

        HashMap<Integer, Integer> result = helper_.sortPlayersByScoreDescending(scoreBoard);

        List<Map.Entry<Integer, Integer>> entryList = new ArrayList<Map.Entry<Integer, Integer>>(result.entrySet());
        int topScore = entryList.get(0).getValue(), topPlayer = entryList.get(0).getKey(),
                bottomScore = entryList.get(3).getValue(), bottomPlayer = entryList.get(3).getKey();

        assertEquals(43, topScore);
        assertEquals(4, topPlayer);
        assertEquals(2, bottomScore);
        assertEquals(3, bottomPlayer);
    }

    /**
     * Assuming brand new deck and no card has been dealt.
     * All 52 cards should have a count of 1.
     *
     */
    @Test
    public void givenDeck_calculateRemainingCardCount_returnCardCount() throws Exception {
        Stack<Card> deck = new Deck(true).getDeck();

        boolean hasCard = true;

        LinkedHashMap<String, Integer> counts = helper_.calculateAllCardCount(deck);
        for (Integer count : counts.values()) {
            if (count != 1) {
                hasCard = false;
                break;
            }
        }
        assertTrue(hasCard);
    }

}
