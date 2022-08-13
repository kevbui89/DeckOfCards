package com.spring.deckofcards.model;

import com.spring.deckofcards.model.deck.Card;
import com.spring.deckofcards.model.deck.Deck;
import com.spring.deckofcards.model.deck.Suit;
import com.spring.deckofcards.model.entities.Game;
import com.spring.deckofcards.util.DeckUtil;
import com.spring.deckofcards.util.IntegerComparator;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.*;

@AllArgsConstructor
@Getter
public class GameManager {

    private static Map<Integer, Stack<Card>> hands_ = new HashMap<Integer, Stack<Card>>();

    private static final GameHelper helper_ = GameHelper.create();

    private static int deckLimit_ = 0;

    private static final Shoe shoe_ = new Shoe();

    /**
     * Returns the shoe.
     * @return The shoe
     */
    public static Shoe getShoe() {
        return shoe_;
    }

    public static Stack<Card> getDeck_() {
        return shoe_.getDeck();
    }

    public static int getDeckLimit() {
        return deckLimit_;
    }

    /**
     * Assumes games already starts with a Deck
     * If you add a deck, you will add 52 cards to the current deck.
     * It is also assumed the new added deck will be shuffled.
     */
    public static int addDeck() {
        deckLimit_++;
        shoe_.addDeck();
        return deckLimit_;
    }

    /**
     * Deals a card to the Player
     */
    public static Card deal(Long playerId) {
        hands_ = shoe_.deal(playerId, hands_);
        return hands_.get(Math.toIntExact(playerId))
                .get(hands_.get(Math.toIntExact(playerId)).size() - 1);
    }

    /**
     * Returns the cards of a player
     *
     * @param playerId The player ID
     * @return Returns the hand of the player
     */
    public static Stack<Card> getHand(Long playerId) {
        hands_.computeIfAbsent(Math.toIntExact(playerId), k -> new Stack<Card>());
        return hands_.get(Math.toIntExact(playerId));
    }

    /**
     * Shuffles deck of cards
     */
    public static String shuffle() {
        DeckUtil.shuffle(shoe_.getDeck());
        return "Deck shuffled";
    }

    /**
     * Returns a shuffled deck
     * @return
     */
    public static Stack<Card> getShuffledDeck() {
        shuffle();
        return shoe_.getDeck();
    }

    /**
     * Returns a hashmap with all the players and their score
     *
     * @return The hashmap container the score of each player
     */
    public static Map<Integer, Integer> getHandScore() {
        return helper_.calculateScoreBoard(hands_);
    }

    /**
     * Returns a LinkedHashMap of players and their score
     * Descending sort by score
     *
     * @return The hashmap containing the scoreboard sorted descending
     */
    public static Map<Integer, Integer> getPlayersSortedByScoreDescending() {
        return helper_.sortPlayersByScoreDescending(helper_.calculateScoreBoard(hands_));
    }

    /**
     * Returns a hashmap with all the card counts in rank value.
     * @return The hashmmap with all card counts
     */
    public static LinkedHashMap<String, Integer> getCardCount() {
        return helper_.calculateAllCardCount(shoe_.getDeck());
    }

    /**
     * Returns a hashmap of the count of cards per suit in the deck
     *
     * @return The hashmap of the remaining cards in the deck per suit
     */
    public static HashMap<Suit,Integer> getSuitCount() {
        return Shoe.getSuitCount();
    }
}
