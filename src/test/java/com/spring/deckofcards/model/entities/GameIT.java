package com.spring.deckofcards.model.entities;

import com.spring.deckofcards.model.entities.Game;
import com.spring.deckofcards.service.impl.GameServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class GameIT {

    @Mock
    Game game_;
    @Mock
    private GameServiceImpl gameService_;

    @Before
    public void setup() {
        game_ = new Game();
        game_.setId(2L);
        game_.setName("Name Test");
    }

    @Test
    public void givenExistingGame_GetGameById_ReturnGameObjectWithProperId() throws Exception {
        when(gameService_.findById(2L)).thenReturn(game_);

        Game fetchedGame = gameService_.findById(2L);

        assertThat(fetchedGame).isNotNull();
        assertThat(fetchedGame.getName()).isSameAs(game_.getName());
    }

    @Test
    public void givenNewGame_addGame_ReturnNewGameObject() throws Exception {
        when(gameService_.save(any(Game.class))).thenReturn(game_);

        Game savedGame = gameService_.save(game_);

        assertThat(savedGame).isNotNull();
        assertThat(savedGame.getName()).isSameAs(game_.getName());
    }

    @Test
    public void givenGame_updateGame_ReturnUpdatedGameObject() throws Exception {
        when(gameService_.update(2L, game_)).thenReturn(game_);
        game_.setName("New Name");

        Game savedGame = gameService_.update(2L, game_);

        assertThat(savedGame).isNotNull();
        assertThat(savedGame.getName()).isSameAs("New Name");
    }

    @Test
    public void givenGame_deleteGame_VerifyDeleteInvocation() throws Exception {
        final Long id = 2L;

        gameService_.delete(id);

        verify(gameService_, times(1)).delete(id);
    }
}
