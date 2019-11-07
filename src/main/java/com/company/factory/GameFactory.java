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

    //    Possible future implementation. More players could be added in further overloads
//    public Game createGame(String player1Name, String player2Name) {
//        Player player1 = new HumanPlayer(player1Name);
//        Player player2 = new HumanPlayer(player2Name);
//        return new Game(player1, player2);
//    }
}
