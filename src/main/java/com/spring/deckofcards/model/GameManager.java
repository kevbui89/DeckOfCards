package com.spring.deckofcards.model;

import com.spring.deckofcards.model.deck.Card;
import com.spring.deckofcards.model.deck.Deck;
import com.spring.deckofcards.model.deck.Suit;
import com.spring.deckofcards.util.DeckUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.*;

@AllArgsConstructor
@Getter
public class GameManager {

    private static Map<Integer, Stack<Card>> hands_ = new HashMap<Integer, Stack<Card>>();

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
        return calculateScoreBoard(hands_);
    }

    /**
     * Returns a LinkedHashMap of players and their score
     * Descending sort by score
     *
     * @return The hashmap containing the scoreboard sorted descending
     */
    public static Map<Integer, Integer> getPlayersSortedByScoreDescending() {
        return sortPlayersByScoreDescending(calculateScoreBoard(hands_));
    }

    /**
     * Returns a hashmap with all the card counts in rank value.
     * @return The hashmmap with all card counts
     */
    public static LinkedHashMap<String, Integer> getCardCount() {
        return calculateAllCardCount(shoe_.getDeck());
    }

    /**
     * Returns a hashmap of the count of cards per suit in the deck
     *
     * @return The hashmap of the remaining cards in the deck per suit
     */
    public static HashMap<Suit,Integer> getSuitCount() {
        return Shoe.getSuitCount();
    }

    /**
     * Returns a hashmap with all the players and their score
     *
     * @return
     */
    private static Map<Integer, Integer> calculateScoreBoard(Map<Integer, Stack<Card>> hands) {

        HashMap<Integer, Integer> scoreBoard = new HashMap<>();
        // Loops through players
        for (int playerId = 0; playerId < hands.size(); playerId++) {
            // Initialize score to 0 before calculation
            int score = 0;
            for (int card = 0; card < hands.get(playerId).size(); card++) {
                score += hands.get(playerId).get(card).getRank().getValue();
            }
            scoreBoard.put(playerId, score);
        }
        return scoreBoard;
    }

    /**
     * Returns a LinkedHashMap of players and their score
     * Descending sort by score
     *
     * @return
     */
    private static LinkedHashMap<Integer, Integer> sortPlayersByScoreDescending(Map<Integer, Integer> scoreBoard) {
        // Ensure all entries are unique
        Set<Map.Entry<Integer, Integer>> entries = scoreBoard.entrySet();
        List<Map.Entry<Integer, Integer>> listOfEntries = new ArrayList<Map.Entry<Integer, Integer>>(entries);
        // Sort using the defined comparator (p2.points - p1.points)
        listOfEntries.sort(new IntegerComparator());
        // Using linkedhashmap because it keeps the order of keys in which they are inserted
        LinkedHashMap<Integer, Integer> sortedByScore = new LinkedHashMap<Integer, Integer>(listOfEntries.size());

        for (Map.Entry<Integer, Integer> entry : listOfEntries) {
            sortedByScore.put(entry.getKey(), entry.getValue());
        }
        return sortedByScore;
    }

    /**
     * Returns a list of the counts of all the cards in the deck
     * @param currentDeck
     * @return
     */
    private static LinkedHashMap<String, Integer> calculateAllCardCount(Stack<Card> currentDeck) {
        // Using linkedhashmap because it keeps the order of keys in which they are inserted
        LinkedHashMap<String,Integer> counts = new LinkedHashMap<>();
        Deck deck = new Deck(false);

        for (Card deckCard : deck.getDeck()) {
            int count = 0;
            counts.put(deckCard.toString(), 0);
            for (Card currentCard : currentDeck) {
                if (deckCard.equals(currentCard)) {
                    count++;
                    counts.replace(deckCard.toString(), count);
                }
            }
        }

        return counts;
    }

    static class IntegerComparator implements Comparator<Map.Entry<Integer,Integer>> {
        @Override
        public int compare(Map.Entry<Integer, Integer> score1, Map.Entry<Integer, Integer> score2) {
            return score2.getValue().compareTo(score1.getValue());
        }
    }

}
