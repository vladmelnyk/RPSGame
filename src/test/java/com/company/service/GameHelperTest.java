package com.company.service;

import com.company.model.Game;
import com.company.model.Round;
import com.company.model.enumeration.Choice;
import com.company.model.player.Player;
import com.company.model.player.computer.Computer;
import com.company.model.player.computer.SimpleComputer;
import com.company.model.player.human.HumanPlayer;
import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.ArrayList;
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

    @Test
    public void shouldUpdateGameWithPlayerWonRound() {
        String playerName = "player1";
        Player player = new HumanPlayer(playerName);
        SimpleComputer computer = new SimpleComputer(random);

        Game previousGame = new Game(player, computer);
        Choice playerChoice = SCISSORS;
        Choice computerChoice = PAPER;
        Round round = new Round(playerChoice, computerChoice, WON);
        Game newGame = new Game(PLAYING, player, computer, Lists.newArrayList(round));

        Game result = GameHelper.updateGameWithRound(previousGame, round);

        assertThat(result).isEqualTo(newGame);
    }

    @Test
    public void shouldUpdateGameWithComputerWonRound() {
        String playerName = "player1";
        Player player = new HumanPlayer(playerName);
        SimpleComputer computer = new SimpleComputer(random);

        Game previousGame = new Game(player, computer);
        Choice playerChoice = SCISSORS;
        Choice computerChoice = PAPER;
        Round round = new Round(playerChoice, computerChoice, WON);
        Game newGame = new Game(PLAYING, player, computer, Lists.newArrayList(round));

        Game result = GameHelper.updateGameWithRound(previousGame, round);

        assertThat(result).isEqualTo(newGame);
    }

    @Test
    public void shouldUpdateGameStatusForPlayerWon() {
        String playerName = "player1";
        Player player = new HumanPlayer(playerName);
        SimpleComputer computer = new SimpleComputer(random);

        Choice playerChoice = SCISSORS;
        Choice computerChoice = PAPER;
        Round round = new Round(playerChoice, computerChoice, WON);
        player.setScore(2);
        List<Round> rounds = new ArrayList<>();
        rounds.add(round);
        rounds.add(round);
        Game previousGame = new Game(PLAYING, player, computer, rounds);

        List<Round> newRounds = Lists.newArrayList(rounds);
        newRounds.add(round);

        Player newPlayer = new HumanPlayer(playerName);
        newPlayer.setScore(3);
        Game newGame = new Game(PLAYER_WON, newPlayer, computer, newRounds);

        Game result = GameHelper.updateGameWithRound(previousGame, round);

        assertThat(result).isEqualTo(newGame);
    }


    @Test
    public void shouldUpdateGameStatusForComputerWon() {
        String playerName = "player1";
        Player player = new HumanPlayer(playerName);
        SimpleComputer computer = new SimpleComputer(random);

        Choice playerChoice = PAPER;
        Choice computerChoice = SCISSORS;
        Round round = new Round(playerChoice, computerChoice, LOST);
        computer.setScore(2);
        List<Round> rounds = new ArrayList<>();
        rounds.add(round);
        rounds.add(round);
        Game previousGame = new Game(PLAYING, player, computer, rounds);

        List<Round> newRounds = Lists.newArrayList(rounds);
        newRounds.add(round);

        Computer newComputer = new SimpleComputer(random);
        newComputer.setScore(3);
        Game newGame = new Game(COMPUTER_WON, player, newComputer, newRounds);

        Game result = GameHelper.updateGameWithRound(previousGame, round);

        assertThat(result).isEqualTo(newGame);
    }
}