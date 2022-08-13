package com.spring.deckofcards.util;

import java.util.Comparator;
import java.util.Map;

public class IntegerComparator implements Comparator<Map.Entry<Integer,Integer>> {
    @Override
    public int compare(Map.Entry<Integer, Integer> score1, Map.Entry<Integer, Integer> score2) {
        return score2.getValue().compareTo(score1.getValue());
    }
}
