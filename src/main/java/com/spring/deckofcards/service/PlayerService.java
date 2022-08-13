package com.spring.deckofcards.service;

import com.spring.deckofcards.DTO.PlayerDTO;
import com.spring.deckofcards.model.entities.Player;

import java.util.List;

public interface PlayerService {
    List<?> findAll();
    Player findById(Long id);
    Player save(PlayerDTO player);
    Player update(Long id, Player player);
    void delete(Long id);
}
