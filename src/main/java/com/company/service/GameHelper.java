package com.company.service;

import com.company.model.Game;
import com.company.model.Round;
import com.company.model.enumeration.GameStatus;
import com.company.model.player.Player;
import com.company.model.player.computer.Computer;

import java.util.ArrayList;
import java.util.List;

import static com.company.model.enumeration.GameStatus.*;
import static com.company.model.enumeration.RoundResult.LOST;
import static com.company.model.enumeration.RoundResult.WON;

class GameHelper {
    private static final int WIN_SCORE = 3;

    private GameHelper() {
    }

    static Game updateGameWithRound(Game game, Round round) {
        Player player = incrementScoreForPlayer(round, game.getPlayer());
        Computer computer = incrementScoreForComputer(round, game.getComputer());
        GameStatus gameStatus = getCurrentGameStatus(player, computer);
        List<Round> rounds = copyAndAddRound(round, game.getRounds());

        return new Game(gameStatus, player, computer, rounds);
    }

    private static GameStatus getCurrentGameStatus(Player player, Player computer) {
        if (player.getScore() >= WIN_SCORE) {
            return PLAYER_WON;
        } else if (computer.getScore() >= WIN_SCORE) {
            return COMPUTER_WON;
        } else {
            return PLAYING;
        }
    }

    private static Player incrementScoreForPlayer(Round round, Player player) {
        if (round.getRoundResult() == WON) {
            int currentScore = player.getScore();
            player.setScore(currentScore + 1);
        }
        return player;
    }

    private static Computer incrementScoreForComputer(Round round, Computer computer) {
        if (round.getRoundResult() == LOST) {
            int currentScore = computer.getScore();
            computer.setScore(currentScore + 1);
        }
        return computer;
    }

    private static List<Round> copyAndAddRound(Round round, List<Round> rounds) {
        List<Round> newRoundsList = new ArrayList<>(rounds);
        newRoundsList.add(round);
        return newRoundsList;
    }
}
