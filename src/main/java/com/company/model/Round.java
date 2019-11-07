package com.company.model;

import com.company.model.enumeration.Choice;
import com.company.model.enumeration.RoundResult;

import java.util.Objects;

public class Round {
    private Choice playerChoice;
    private Choice computerChoice;
    private RoundResult roundResult;

    public RoundResult getRoundResult() {
        return roundResult;
    }

    public Choice getPlayerChoice() {
        return playerChoice;
    }

    public Choice getComputerChoice() {
        return computerChoice;
    }


    public Round() {
    }

    public Round(Choice playerChoice, Choice computerChoice, RoundResult roundResult) {
        this.playerChoice = playerChoice;
        this.computerChoice = computerChoice;
        this.roundResult = roundResult;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Round round = (Round) o;
        return getPlayerChoice() == round.getPlayerChoice() &&
                getComputerChoice() == round.getComputerChoice() &&
                getRoundResult() == round.getRoundResult();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPlayerChoice(), getComputerChoice(), getRoundResult());
    }
}
