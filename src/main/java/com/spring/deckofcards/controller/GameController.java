package com.spring.deckofcards.controller;

import com.spring.deckofcards.model.GameManager;
import com.spring.deckofcards.model.entities.Game;
import com.spring.deckofcards.model.entities.Player;
import com.spring.deckofcards.service.GameService;
import com.spring.deckofcards.service.PlayerService;
import io.swagger.v3.oas.annotations.Operation;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/game")
public class GameController {

    @Autowired
    private GameService gameService_;

    @Autowired
    private PlayerService playerService_;
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

    @Operation(summary = "Returns the list of players in the game")
    @GetMapping("/{id}/players")
    public ResponseEntity<?> getPlayerList(@PathVariable Long id) throws NotFoundException {
        Game game = gameService_.findById(id);
        return ResponseEntity.ok(game.getPlayers());
    }

    @Operation(summary = "Returns the count for each suit in the deck")
    @GetMapping("/{id}/suitcount")
    public ResponseEntity<?> getSuitCount(@PathVariable Long id) {
        //return ResponseEntity.ok(GameManager.getRemainingCardInDeckPerSuit());
        return ResponseEntity.ok(GameManager.getSuitCount());
    }

    @Operation(summary = "Shuffles the deck of game with the corresponding ID")
    @GetMapping("/{id}/shuffle")
    public ResponseEntity<?> shuffle(@PathVariable Long id) {
        return ResponseEntity.ok(GameManager.shuffle());
    }

    @Operation(summary = "Adds a deck to a game")
    @GetMapping("/{id}/adddeck")
    public ResponseEntity<?> addDeck(@PathVariable Long id) {
        Game game = gameService_.findById(id);
        if (GameManager.getDeckLimit() < 10) {
            return game == null ? ResponseEntity.unprocessableEntity().body(id) : ResponseEntity.ok(GameManager.addDeck());
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You cannot add more than 10 decks.");
    }

    @Operation(summary = "Returns a specific player's hand")
    @GetMapping("/{id}/playerhand/{pId}")
    public ResponseEntity<?> getPlayerHand(@PathVariable Long id, @PathVariable Long pId) {
        Game game = gameService_.findById(id);
        for (Player p : game.getPlayers()) {
            return ResponseEntity.ok().body(GameManager.getHand(pId));
        }
        return ResponseEntity.unprocessableEntity().build();
    }

    @Operation(summary = "Deal a card to a player")
    @GetMapping("/{id}/deal/{pId}")
    public ResponseEntity<?> dealCard(@PathVariable Long id, @PathVariable Long pId) {
        Game game = gameService_.findById(id);
        Player p = playerService_.findById(pId);
        if (!GameManager.getDeck_().isEmpty()) {
            p.setPoints(p.getPoints()
                    + Objects.requireNonNull(GameManager.deal(pId)).getRank().getValue());
        }
        playerService_.update(pId, p);
        return ResponseEntity.ok(p);
    }

    @Operation(summary = "Get the remaining card count from the deck")
    @GetMapping("/{id}/cardcount")
    public ResponseEntity<?> remainingCardCount(@PathVariable Long id) {
        return ResponseEntity.ok().body(GameManager.getCardCount());
    }

    @Operation(summary = "Get the player points")
    @GetMapping("/{id}/points/{pId}")
    public ResponseEntity<?> getPlayerScore(@PathVariable Long id, @PathVariable Long pId) {
        Player p = playerService_.findById(pId);
        if (p == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(p.getPoints());
    }
}
