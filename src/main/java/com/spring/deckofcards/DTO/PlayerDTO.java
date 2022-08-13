package com.spring.deckofcards.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Player Data Transfer Object
 */

@AllArgsConstructor
@Data
public class PlayerDTO {

    private Long id;

    private String name;

    private int points;

    private Long game_id;
}
