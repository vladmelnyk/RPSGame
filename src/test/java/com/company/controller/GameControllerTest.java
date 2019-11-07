package com.company.controller;

import com.company.exception.GameNotFoundException;
import com.company.exception.GameOverException;
import com.company.model.Game;
import com.company.model.enumeration.Choice;
import com.company.model.enumeration.GameStatus;
import com.company.model.player.computer.SimpleComputer;
import com.company.model.player.human.HumanPlayer;
import com.company.service.GameService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Random;

import static com.company.model.enumeration.Choice.SCISSORS;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(GameController.class)
public class GameControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GameService gameService;

    private ObjectMapper objectMapper = new ObjectMapper();
    private Choice choice = SCISSORS;
    private Integer gameId = 0;
    private String playerName = "player";
    private Game game = new Game(GameStatus.PLAYING,
            new HumanPlayer(playerName),
            new SimpleComputer(new Random()),
            Lists.newArrayList());

    @Test
    public void shouldCreateGameSuccessfully() throws Exception {
        when(gameService.createGame(playerName)).thenReturn(0);

        this.mockMvc.perform(post("/games").param("playerName", playerName))
                .andExpect(status().isOk())
                .andExpect(content().string("0"));
    }

    @Test
    public void shouldPlayGameSuccessfully() throws Exception {
        when(gameService.play(gameId, choice)).thenReturn(game);

        this.mockMvc.perform(post("/games/0").param("choice", choice.toString()))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(game)));
    }

    @Test
    public void badRequestForUnknownChoice() throws Exception {
        this.mockMvc.perform(post("/games/0").param("choice", "null"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testGameNotFound() throws Exception {
        when(gameService.play(gameId, choice)).thenThrow(GameNotFoundException.class);

        this.mockMvc.perform(post("/games/0").param("choice", choice.toString()))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGameIsOver() throws Exception {
        when(gameService.play(gameId, choice)).thenThrow(GameOverException.class);

        this.mockMvc.perform(post("/games/0").param("choice", choice.toString()))
                .andExpect(status().isConflict());
    }

    @Test
    public void shouldGetGameSuccessfully() throws Exception {
        when(gameService.getGame(gameId)).thenReturn(game);

        this.mockMvc.perform(get("/games/0"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(game)));
    }

    @Test
    public void getGameNotFound() throws Exception {
        when(gameService.getGame(gameId)).thenThrow(GameNotFoundException.class);

        this.mockMvc.perform(get("/games/0"))
                .andExpect(status().isNotFound());
    }
}