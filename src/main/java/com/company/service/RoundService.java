package com.company.service;

import com.company.exception.ChoiceIsInvalidException;
import com.company.model.Round;
import com.company.model.enumeration.Choice;
import com.company.model.enumeration.RoundResult;
import com.company.repository.inmemory.RoundInMemoryRepository;
import org.springframework.stereotype.Service;

import static com.company.model.enumeration.RoundResult.*;

@Service
public class RoundService {

    private RoundInMemoryRepository roundRepository;

    public RoundService(RoundInMemoryRepository roundRepository) {
        this.roundRepository = roundRepository;
    }

    Round createRound(Choice playerOneChoice, Choice playerTwoChoice) {
        RoundResult roundResult = evaluateChoices(playerOneChoice, playerTwoChoice);
        Round round = new Round(playerOneChoice, playerTwoChoice, roundResult);
        roundRepository.create(round);
        return round;
    }

    private RoundResult evaluateChoices(Choice playerOneChoice, Choice playerTwoChoice) {
        validateChoice(playerOneChoice);
        if (playerOneChoice.beats(playerTwoChoice)) {
            return WON;
        } else if (playerTwoChoice.beats(playerOneChoice)) {
            return LOST;
        } else {
            return DRAW;
        }
    }

    private void validateChoice(Choice playerOneChoice) {
        if (playerOneChoice == null) {
            throw new ChoiceIsInvalidException("Choice cannot be empty");
        }
    }
}
