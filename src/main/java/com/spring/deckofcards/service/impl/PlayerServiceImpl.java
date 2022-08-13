package com.spring.deckofcards.service.impl;

import com.spring.deckofcards.DTO.PlayerDTO;
import com.spring.deckofcards.model.entities.Game;
import com.spring.deckofcards.model.entities.Player;
import com.spring.deckofcards.repository.GameRepository;
import com.spring.deckofcards.repository.PlayerRepository;
import com.spring.deckofcards.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;

@Service
public class PlayerServiceImpl implements PlayerService {

    @Autowired
    private PlayerRepository playerRepository_;

    @Autowired
    private GameRepository gameRepository_;

    @Override
    public List<?> findAll() {
        return playerRepository_.findAll();
    }

    @Override
    public Player findById(Long id) {
        return playerRepository_.findById(id)
                .orElseThrow(() -> new NotFoundException("Invalid player(" + id + ")"));
    }

    @Override
    public Player save(PlayerDTO player) {
        Game game = gameRepository_.findById(player.getGame_id())
                .orElseThrow(() -> new NotFoundException("Invalid game(" + player.getGame_id() + ")"));
        Player savedPlayer = new Player(player.getId(), player.getName(), player.getPoints(), game);
        return playerRepository_.save(savedPlayer);
    }

    @Override
    public Player update(Long id, Player player) {
        playerRepository_.findById(id)
                .orElseThrow(() -> new NotFoundException("Invalid player(" + id + ")"));
        player.setId(id);
        return playerRepository_.save(player);
    }

    @Override
    public void delete(Long id) {
        playerRepository_.findById(id).ifPresent(Player -> playerRepository_.delete(Player));
    }
}
