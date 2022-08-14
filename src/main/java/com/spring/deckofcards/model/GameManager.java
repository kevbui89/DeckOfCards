package com.spring.deckofcards.model;

import com.spring.deckofcards.helper.GameHelper;
import com.spring.deckofcards.model.deck.Card;
import com.spring.deckofcards.model.deck.Suit;
import com.spring.deckofcards.util.DeckUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.*;

@AllArgsConstructor
@Getter
public class GameManager {

    private static HashMap<Integer, Shoe> shoes_ = new HashMap<>();

    private static Map<Integer, Stack<Card>> hands_ = new HashMap<Integer, Stack<Card>>();

    private static GameHelper helper_ = GameHelper.create();

    /**
     * Returns the shoe corresponding to the game ID.
     * @param gameId The game ID
     * @return The Shoe of the passed ID
     */
    public static Shoe getShoe(Long gameId) {
        if (shoes_.get(Math.toIntExact(gameId)) == null) {
            shoes_.put(Math.toIntExact(gameId), new Shoe());
        }
        return shoes_.get(Math.toIntExact(gameId));
    }

    public static Stack<Card> getDeck(Long id) {
        return getShoe(id).getDeck();
    }

    public static int getDeckLimit(Long id) {
        return getShoe(id).getDeckLimit();
    }

    /**
     * Assumes games already starts with a Deck
     * If you add a deck, you will add 52 cards to the current deck.
     * It is also assumed the new added deck will be shuffled.
     */
    public static int addDeck(Long id) {
        getShoe(id).incrementDeckLimit();
        getShoe(id).addDeck();
        return getShoe(id).getDeckLimit();
    }

    /**
     * Deals a card to the Player
     */
    public static Card deal(Long id, Long playerId) {
        return getShoe(id).deal(playerId, hands_);
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
    public static String shuffle(Long id) {
        DeckUtil.shuffle(getShoe(id).getDeck());
        return "Deck shuffled";
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
    public static LinkedHashMap<String, Integer> getCardCount(Long id) {
        return helper_.calculateAllCardCount(getShoe(id).getDeck());
    }

    /**
     * Returns a hashmap of the count of cards per suit in the deck
     *
     * @return The hashmap of the remaining cards in the deck per suit
     */
    public static HashMap<Suit,Integer> getSuitCount(Long id) {
        return getShoe(id).getSuitCount();
    }
}
