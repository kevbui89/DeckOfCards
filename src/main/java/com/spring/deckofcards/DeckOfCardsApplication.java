package com.spring.deckofcards;

import com.spring.deckofcards.DTO.PlayerDTO;
import com.spring.deckofcards.model.entities.Game;
import com.spring.deckofcards.service.GameService;
import com.spring.deckofcards.service.PlayerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Slf4j
@SpringBootApplication
@EnableJpaRepositories
public class DeckOfCardsApplication {

	public static void main(String[] args) {
		SpringApplication.run(DeckOfCardsApplication.class, args);
	}
	@Bean
	public CommandLineRunner insertData(GameService gameService, PlayerService playerService) {
		return (args) -> {
			// Save a game and a player
			gameService.save(new Game("Game1"));
			gameService.save(new Game("Game2"));
			playerService.save(new PlayerDTO(1L,"Kevin", 0, 1L));
			playerService.save(new PlayerDTO(2L,"Vince", 0, 1L));
			playerService.save(new PlayerDTO(3L,"David", 0, 2L));
		};
	}
}
