package com.company.controller;

import com.company.exception.GameNotFoundException;
import com.company.exception.GameOverException;
import com.company.model.Game;
import com.company.model.enumeration.Choice;
import com.company.service.GameService;
import org.springframework.web.bind.annotation.*;

@RestController
public class GameController {

    private GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    //    Could be extended with additional player2Name for human vs human game
    @PostMapping("/games")
    Integer createGame(@RequestParam String playerName) {
        return gameService.createGame(playerName);
    }

    @PostMapping("/games/{id}")
    Game playGame(@PathVariable("id") int id, Choice choice) throws GameOverException, GameNotFoundException {
        return gameService.play(id, choice);
    }

    @GetMapping("/games/{id}")
    Game getGame(@PathVariable("id") int id) throws GameNotFoundException {
        return gameService.getGame(id);
    }
}
