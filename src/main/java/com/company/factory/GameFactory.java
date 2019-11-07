package com.company.factory;

import com.company.model.Game;
import com.company.model.player.Player;
import com.company.model.player.computer.SimpleComputer;
import com.company.model.player.human.HumanPlayer;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class GameFactory {
    private Random random;

    public GameFactory(Random random) {
        this.random = random;
    }

    //    factory method
    public Game createGame(String playerName) {
        Player player = new HumanPlayer(playerName);
        return new Game(player, new SimpleComputer(random));
    }
}
