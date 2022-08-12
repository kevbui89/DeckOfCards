package com.spring.deckofcards.service.impl;

import com.spring.deckofcards.model.Game;
import com.spring.deckofcards.service.GameService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameServiceImpl implements GameService {
    @Override
    public List<Game> findAll() {
        return null;
    }

    @Override
    public Game findById(Long id) {
        return null;
    }

    @Override
    public Game save(Game game) {
        return null;
    }

    @Override
    public Game update(Long id, Game game) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
