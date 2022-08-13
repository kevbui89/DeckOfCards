package com.spring.deckofcards.controller;

import com.spring.deckofcards.model.entities.Game;
import com.spring.deckofcards.service.GameService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/game")
public class GameController {

    @Autowired
    private GameService gameService_;
    @Operation(summary = "Find all existing games")
    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok().body(gameService_.findAll());
    }

    @Operation(summary = "Find game by ID")
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(gameService_.findById(id));
    }

    @Operation(summary = "Update game information by ID")
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Game game) {
        return ResponseEntity.ok().body(gameService_.update(id, game));
    }

    @Operation(summary = "Save a new game")
    @PostMapping
    public ResponseEntity<?> save(@RequestBody Game game) {
        return ResponseEntity.ok().body(gameService_.save(game));
    }

    @Operation(summary = "Delete a game by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        gameService_.delete(id);
        return ResponseEntity.noContent().build();
    }

}
