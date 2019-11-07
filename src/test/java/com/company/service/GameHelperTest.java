package com.company.service;

import com.company.model.Game;
import com.company.model.Round;
import com.company.model.player.Player;
import com.company.model.player.computer.Computer;
import com.company.model.player.computer.SimpleComputer;
import com.company.model.player.human.HumanPlayer;
import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.List;
import java.util.Random;

import static com.company.model.enumeration.Choice.PAPER;
import static com.company.model.enumeration.Choice.SCISSORS;
import static com.company.model.enumeration.GameStatus.*;
import static com.company.model.enumeration.RoundResult.LOST;
import static com.company.model.enumeration.RoundResult.WON;
import static org.assertj.core.api.Assertions.assertThat;

public class GameHelperTest {

    private Random random = new Random();

    private String playerName = "player1";
    private Player player = new HumanPlayer(playerName);
    private SimpleComputer computer = new SimpleComputer(random);

    @Test
    public void shouldUpdateGameWithPlayerWonRound() {
        Game previousGame = new Game(player, computer);
        Round round = new Round(SCISSORS, PAPER, WON);
        Game newGame = new Game(PLAYING, player, computer, Lists.newArrayList(round));

        Game result = GameHelper.updateGameWithRound(previousGame, round);

        assertThat(result).isEqualTo(newGame);
    }

    @Test
    public void shouldUpdateGameWithComputerWonRound() {
        Game previousGame = new Game(player, computer);
        Round round = new Round(SCISSORS, PAPER, WON);
        Game newGame = new Game(PLAYING, player, computer, Lists.newArrayList(round));

        Game result = GameHelper.updateGameWithRound(previousGame, round);

        assertThat(result).isEqualTo(newGame);
    }

    @Test
    public void shouldUpdateGameStatusForPlayerWon() {
        Round round = new Round(SCISSORS, PAPER, WON);
//       setup current game
        player.setScore(2);
        List<Round> rounds = Lists.newArrayList(round, round);
        Game currentGame = new Game(PLAYING, player, computer, rounds);
//       setup target game
        List<Round> newRounds = Lists.newArrayList(round, round, round);
        Player newPlayer = new HumanPlayer(playerName);
        newPlayer.setScore(3);
        Game targetGame = new Game(PLAYER_WON, newPlayer, computer, newRounds);

        Game result = GameHelper.updateGameWithRound(currentGame, round);

        assertThat(result).isEqualTo(targetGame);
    }


    @Test
    public void shouldUpdateGameStatusForComputerWon() {
        Round round = new Round(PAPER, SCISSORS, LOST);
//       setup current game
        computer.setScore(2);
        List<Round> rounds = Lists.newArrayList(round, round);
        Game currentGame = new Game(PLAYING, player, computer, rounds);
//       setup target game
        List<Round> newRounds = Lists.newArrayList(round, round, round);
        Computer newComputer = new SimpleComputer(random);
        newComputer.setScore(3);
        Game targetGame = new Game(COMPUTER_WON, player, newComputer, newRounds);

        Game result = GameHelper.updateGameWithRound(currentGame, round);

        assertThat(result).isEqualTo(targetGame);
    }
}