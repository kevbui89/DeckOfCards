package com.spring.deckofcards.model;

import com.spring.deckofcards.DTO.PlayerDTO;
import com.spring.deckofcards.service.impl.PlayerServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PlayerIT {

    @Mock
    private PlayerServiceImpl playerService_;

    @Mock
    Player player_;

    @Before
    public void setup() {
        player_ = new Player();
        player_.setId(2L);
        player_.setName("Name Test");
        player_.setPoints(0);
    }

    @Test
    public void givenExistingPlayer_GetPlayerById_ReturnPlayerObjectWithProperId() throws Exception {
        when(playerService_.findById(2L)).thenReturn(player_);

        Player fetchedPlayer = playerService_.findById(2L);

        assertThat(fetchedPlayer).isNotNull();
        assertThat(fetchedPlayer.getName()).isSameAs(player_.getName());
    }

    @Test
    public void givenNewPlayer_addPlayer_ReturnNewPlayerObject() throws Exception {
        when(playerService_.save(any(PlayerDTO.class))).thenReturn(player_);

        Player savedPlayer = playerService_.save(new PlayerDTO(2L, player_.getName(), player_.getPoints(), 1L));

        assertThat(savedPlayer).isNotNull();
        assertThat(savedPlayer.getName()).isSameAs(player_.getName());
    }

    @Test
    public void givenPlayer_updatePlayer_ReturnUpdatedPlayerObject() throws Exception {
        when(playerService_.update(2L, player_)).thenReturn(player_);
        player_.setName("New Name");

        Player savedPlayer = playerService_.update(2L, player_);

        assertThat(savedPlayer).isNotNull();
        assertThat(savedPlayer.getName()).isSameAs("New Name");
    }

    @Test
    public void givenPlayer_deletePlayer_VerifyDeleteInvocation() throws Exception {
        final Long id = 2L;

        playerService_.delete(id);

        verify(playerService_, times(1)).delete(id);
    }
}
