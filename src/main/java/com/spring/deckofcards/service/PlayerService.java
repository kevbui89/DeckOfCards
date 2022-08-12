package com.spring.deckofcards.service;

import com.spring.deckofcards.model.Player;

import java.util.List;

public interface PlayerService {
    List<?> findAll();
    Player findById(Long id);
    Player save(Player player);
    Player update(Long id, Player player);
    void delete(Long id);
}
