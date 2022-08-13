package com.spring.deckofcards.model;

import com.spring.deckofcards.model.deck.Card;
import com.spring.deckofcards.model.deck.Deck;
import com.spring.deckofcards.model.deck.Suit;
import com.spring.deckofcards.util.IntegerComparator;

import java.util.*;

public class GameHelper {

    private GameHelper() {
        // hide constructor
    }

    public static GameHelper create() {
        return new GameHelper();
    }

    /**
     * Returns a hashmap with all the players and their score
     *
     * @return
     */
    public Map<Integer, Integer> calculateScoreBoard(Map<Integer, Stack<Card>> hands) {

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
    public LinkedHashMap<Integer, Integer> sortPlayersByScoreDescending(Map<Integer, Integer> scoreBoard) {
        Set<Map.Entry<Integer, Integer>> entries = scoreBoard.entrySet();
        List<Map.Entry<Integer, Integer>> listOfEntries = new ArrayList<Map.Entry<Integer, Integer>>(entries);
        listOfEntries.sort(new IntegerComparator());
        LinkedHashMap<Integer, Integer> sortedByScore = new LinkedHashMap<Integer, Integer>(listOfEntries.size());

        for (Map.Entry<Integer, Integer> entry : listOfEntries) {
            sortedByScore.put(entry.getKey(), entry.getValue());
        }
        return sortedByScore;
    }

    /**
     * Calculates the remaining number of cards from each suit in a deck.
     *
     * @param deck
     * @return
     */
    @Deprecated
    public Map<Suit, Integer> calculateRemainingCardsPerSuit(Stack<Card> deck) {
        //Refactor
        int spades = 0, hearts = 0, clubs = 0, diamonds = 0;

        for (Card card : deck) {
            switch (card.getSuit()) {
                case CLUBS -> clubs++;
                case HEARTS -> hearts++;
                case SPADES -> spades++;
                case DIAMONDS -> diamonds++;
            }
        }

        Map<Suit, Integer> suitCount = new HashMap<>();
        suitCount.put(Suit.CLUBS, clubs);
        suitCount.put(Suit.HEARTS, hearts);
        suitCount.put(Suit.SPADES, spades);
        suitCount.put(Suit.DIAMONDS, diamonds);

        return suitCount;
    }

    /**
     * Returns a list of the counts of all the cards in the deck
     * @param currentDeck
     * @return
     */
    public LinkedHashMap<String, Integer> calculateAllCardCount(Stack<Card> currentDeck) {
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
}