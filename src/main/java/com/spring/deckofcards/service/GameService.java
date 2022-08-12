package com.spring.deckofcards.service;

import com.spring.deckofcards.model.Game;

import java.util.List;

public interface GameService {
    List<Game> findAll();
    Game findById(Long id);
    Game save(Game game);
    Game update(Long id, Game game);
    void delete(Long id);
}
