package com.spring.deckofcards.controller;

import com.spring.deckofcards.DTO.PlayerDTO;
import com.spring.deckofcards.model.entities.Player;
import com.spring.deckofcards.service.PlayerService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/player")
public class PlayerController {

    @Autowired
    private PlayerService playerService_;

    @Operation(summary = "Find all existing players")
    @GetMapping
    public ResponseEntity<List<?>> findAll() {
        return ResponseEntity.ok().body(playerService_.findAll());
    }

    @Operation(summary = "Find a player by ID")
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(playerService_.findById(id));
    }

    @Operation(summary = "Save a new player")
    @PostMapping
    public ResponseEntity<?> save(@RequestBody PlayerDTO player) {
        return ResponseEntity.ok().body(playerService_.save(player));
    }

    @Operation(summary = "Delete a player by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        playerService_.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Update a player information by ID")
    @PutMapping("{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Player player) {
        return ResponseEntity.ok().body(playerService_.update(id, player));
    }
}
