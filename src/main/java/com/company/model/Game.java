package com.company.model;

import com.company.model.enumeration.GameStatus;
import com.company.model.player.Player;
import com.company.model.player.computer.Computer;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Game {
    private GameStatus gameStatus;
    private Player player;
    private Computer computer;
    private List<Round> rounds;


    public Game(Player player, Computer computer) {
        this.player = player;
        this.computer = computer;
        this.rounds = new ArrayList<>();
        this.gameStatus = GameStatus.PLAYING;
    }

    public Game(GameStatus gameStatus, Player player, Computer computer, List<Round> rounds) {
        this.gameStatus = gameStatus;
        this.player = player;
        this.computer = computer;
        this.rounds = rounds;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Computer getComputer() {
        return computer;
    }

    public void setComputer(Computer computer) {
        this.computer = computer;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public List<Round> getRounds() {
        return rounds;
    }

    public void setRounds(List<Round> rounds) {
        this.rounds = rounds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return getGameStatus() == game.getGameStatus() &&
                Objects.equals(getPlayer(), game.getPlayer()) &&
                Objects.equals(getComputer(), game.getComputer()) &&
                Objects.equals(getRounds(), game.getRounds());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getGameStatus(), getPlayer(), getComputer(), getRounds());
    }

    @Override
    public String toString() {
        return "Game{" +
                "gameStatus=" + gameStatus +
                ", player=" + player +
                ", computer=" + computer +
                ", rounds=" + rounds +
                '}';
    }
}