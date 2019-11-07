package com.company.service;

import com.company.exception.GameNotFoundException;
import com.company.exception.GameOverException;
import com.company.factory.GameFactory;
import com.company.model.Game;
import com.company.model.Round;
import com.company.model.enumeration.Choice;
import com.company.model.enumeration.GameStatus;
import com.company.repository.inmemory.GameInMemoryRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;
import java.util.Random;

import static com.company.model.enumeration.Choice.PAPER;
import static com.company.model.enumeration.Choice.SCISSORS;
import static com.company.model.enumeration.RoundResult.WON;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.Silent.class)
public class GameServiceTest {

    @Mock
    private GameInMemoryRepository gameInMemoryRepository;

    @Mock
    private RoundService roundService;

    private GameFactory gameFactory = new GameFactory(new Random());

    private GameService gameService;

    private String playerName = "player1";
    private Integer id = 0;

    @Before
    public void init() throws Exception {
        MockitoAnnotations.initMocks(this);
        gameFactory = new GameFactory(new Random());
        gameService = new GameService(gameFactory, gameInMemoryRepository, roundService);
    }


    @Test
    public void shouldCreateGameSuccessfully() {
        Game game = gameFactory.createGame(playerName);
        when(gameInMemoryRepository.create(game)).thenReturn(id);

        Integer resultId = gameService.createGame(playerName);

        assertThat(resultId).isEqualTo(id);
    }

    @Test
    public void shouldPlayRoundSuccessfully() {
        Game game = gameFactory.createGame(playerName);
        Choice playerChoice = SCISSORS;
        Round round = new Round(playerChoice, PAPER, WON);
        when(gameInMemoryRepository.findById(id)).thenReturn(Optional.of(game));
        when(roundService.createRound(any(), any())).thenReturn(round);
        game = GameHelper.updateGameWithRound(game, round);
        doNothing().when(gameInMemoryRepository).update(id, game);

        Game resultGame = gameService.play(id, playerChoice);

        assertThat(resultGame).isEqualTo(game);
    }

    @Test(expected = GameNotFoundException.class)
    public void throwsGameNotFoundException() {
        Choice playerChoice = SCISSORS;

        when(gameInMemoryRepository.findById(id)).thenReturn(Optional.empty());

        gameService.play(id, playerChoice);
    }

    @Test(expected = GameOverException.class)
    public void throwsGameOverExceptionWhenComputerWon() {
        Game game = gameFactory.createGame(playerName);
        game.setGameStatus(GameStatus.COMPUTER_WON);
        Choice playerChoice = SCISSORS;
        when(gameInMemoryRepository.findById(id)).thenReturn(Optional.of(game));

        gameService.play(id, playerChoice);
    }

    @Test(expected = GameOverException.class)
    public void throwsGameOverExceptionWhenPlayerWon() {
        Game game = gameFactory.createGame(playerName);
        game.setGameStatus(GameStatus.PLAYER_WON);
        Choice playerChoice = SCISSORS;
        when(gameInMemoryRepository.findById(id)).thenReturn(Optional.of(game));

        gameService.play(id, playerChoice);
    }


}