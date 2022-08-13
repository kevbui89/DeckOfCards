package com.spring.deckofcards.service.impl;

import com.spring.deckofcards.model.entities.Game;
import com.spring.deckofcards.repository.GameRepository;
import com.spring.deckofcards.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;

@Service
public class GameServiceImpl implements GameService {

    @Autowired
    private GameRepository gameRepository_;
    @Override
    public List<Game> findAll() {
        return gameRepository_.findAll();
    }

    @Override
    public Game findById(Long id) {
        return gameRepository_.findById(id)
                .orElseThrow(() -> new NotFoundException("Invalid game(" + id + ")"));
    }

    @Override
    public Game save(Game game) {
        return gameRepository_.save(game);
    }

    @Override
    public Game update(Long id, Game game) {
        gameRepository_.findById(id)
                .orElseThrow(() -> new NotFoundException("Invalid game(" + id + ")"));
        game.setId(id);
        return gameRepository_.save(game);
    }

    @Override
    public void delete(Long id) {
        gameRepository_.findById(id).ifPresent(Game -> gameRepository_.delete(Game));
    }
}
