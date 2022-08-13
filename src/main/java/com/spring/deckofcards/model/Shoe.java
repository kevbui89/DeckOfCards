package com.spring.deckofcards.model;

import com.spring.deckofcards.model.deck.Card;
import com.spring.deckofcards.model.deck.Deck;
import com.spring.deckofcards.model.deck.Suit;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

@Getter
@Setter
public class Shoe {

    private static final Stack<Card> deck_ = new Deck(true).getDeck();

    private static HashMap<Suit,Integer> counts_ = new HashMap<>();
    // Initialized game has 13 cards of each
    private static int spadesCount_ = 13;
    private static int clubsCount_ = 13;
    private static int heartsCount_ = 13;
    private static int diamondsCount_ = 13;

    public Shoe () {
        counts_ = init();
    }

    /**
     * Assumes games already starts with a Deck
     * If you add a deck, you will add 52 cards to the current deck.
     * It is also assumed the new added deck will be shuffled.
     */
    public void addDeck() {
        addSetToAllSuits();
        Stack<Card> addedDeck = new Deck(true).getDeck();
        for (Card card : addedDeck) {
            deck_.push(card);
        }
    }

    /**
     * Returns the deck
     * @return
     */
    public Stack<Card> getDeck() {
        return deck_;
    }

    /**
     * Returns the hand of the player the card was dealt to.
     * @param playerId
     * @param hands_
     * @return
     */
    public Map<Integer, Stack<Card>> deal(Long playerId, Map<Integer, Stack<Card>> hands_) {
        // Only pop if the deck has at least one card left in the deck
        if (!deck_.isEmpty()) {
            if (hands_.get(Math.toIntExact(playerId)) == null) {
                Stack<Card> newHand = new Stack<>();
                newHand.push(deck_.get(deck_.size() - 1));
                hands_.put(Math.toIntExact(playerId), newHand);
            } else {
                hands_.get(Math.toIntExact(playerId)).push(deck_.get(deck_.size() - 1));
            }
            decrementCardCountForGivenSuit(deck_.get(deck_.size() - 1));
            deck_.pop();
            return hands_;
        }
        return null;
    }

    public static HashMap<Suit,Integer> init() {
        HashMap<Suit,Integer> initialCount = new HashMap<>();
        initialCount.put(Suit.SPADES, spadesCount_);
        initialCount.put(Suit.CLUBS, clubsCount_);
        initialCount.put(Suit.HEARTS, heartsCount_);
        initialCount.put(Suit.DIAMONDS, diamondsCount_);

        return initialCount;
    }

    public static void decrementSpadesCount() {
        counts_.replace(Suit.SPADES, --spadesCount_);
    }

    public static void decrementClubsCount() {
        counts_.replace(Suit.CLUBS, --clubsCount_);
    }

    public static void decrementHeartsCount() {
        counts_.replace(Suit.HEARTS, --heartsCount_);
    }

    public static void decrementDiamondsCount() {
        counts_.replace(Suit.DIAMONDS, --diamondsCount_);
    }

    public static void addSetToAllSuits() {
        counts_.replace(Suit.SPADES, spadesCount_ += 13);
        counts_.replace(Suit.CLUBS, clubsCount_ += 13);
        counts_.replace(Suit.HEARTS, heartsCount_ += 13);
        counts_.replace(Suit.DIAMONDS, diamondsCount_ += 13);
    }

    public static HashMap<Suit,Integer> getSuitCount() {
        return counts_;
    }

    /**
     * Decrements count for given suit
     * @param card The card object
     */
    private static void decrementCardCountForGivenSuit(Card card) {
        switch (card.getSuit()) {
            case CLUBS -> decrementClubsCount();
            case SPADES -> decrementSpadesCount();
            case HEARTS -> decrementHeartsCount();
            case DIAMONDS -> decrementDiamondsCount();
        }
    }
}
