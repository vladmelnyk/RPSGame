package com.company.service;

import com.company.exception.GameNotFoundException;
import com.company.exception.GameOverException;
import com.company.factory.GameFactory;
import com.company.model.Game;
import com.company.model.Round;
import com.company.model.enumeration.Choice;
import com.company.model.enumeration.GameStatus;
import com.company.model.player.computer.Computer;
import com.company.repository.inmemory.GameInMemoryRepository;
import org.springframework.stereotype.Service;


@Service
public class GameService {

    private GameFactory gameFactory;
    private GameInMemoryRepository gameRepository;

    private RoundService roundService;

    public GameService(GameFactory gameFactory, GameInMemoryRepository gameRepository, RoundService roundService) {
        this.gameFactory = gameFactory;
        this.gameRepository = gameRepository;
        this.roundService = roundService;
    }

    public Integer createGame(String playerName) {
        Game game = gameFactory.createGame(playerName);
        return gameRepository.create(game);
    }

    public Game play(int gameId, Choice choice) {
        Game game = getGame(gameId);
        validateGameStatus(game);

        Choice computerChoice = getChoiceFromComputer(game);

        Round round = roundService.createRound(choice, computerChoice);

        game = GameHelper.updateGameWithRound(game, round);
        gameRepository.update(gameId, game);
        return game;
    }

    public Game getGame(int id) {
        return gameRepository.findById(id)
                .orElseThrow(() -> new GameNotFoundException("Game not found"));
    }

    private Choice getChoiceFromComputer(Game game) {
        Computer computer = game.getComputer();
        return computer.selectChoice();
    }


    private void validateGameStatus(Game game) {
        if (!GameStatus.PLAYING.equals(game.getGameStatus())) {
            throw new GameOverException("Game is over, please create new game");
        }
    }
}
