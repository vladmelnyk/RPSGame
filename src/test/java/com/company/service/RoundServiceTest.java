package com.company.service;

import com.company.exception.ChoiceIsInvalidException;
import com.company.model.Round;
import com.company.model.enumeration.Choice;
import com.company.repository.inmemory.RoundInMemoryRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static com.company.model.enumeration.Choice.*;
import static com.company.model.enumeration.RoundResult.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.Silent.class)
public class RoundServiceTest {

    @Mock
    private RoundInMemoryRepository roundInMemoryRepository;

    @InjectMocks
    private RoundService roundService;

    @Before
    public void setUp() {
        when(roundInMemoryRepository.create(any())).thenReturn(0);
    }


    @Test
    public void shouldCreateRoundSuccessfully() {
        Choice playerChoice = SCISSORS;
        Choice computerChoice = PAPER;
        Round expectedRound = new Round(playerChoice, computerChoice, WON);

        Round round = roundService.createRound(playerChoice, computerChoice);

        assertThat(round).isEqualTo(expectedRound);
    }

    @Test(expected = ChoiceIsInvalidException.class)
    public void shouldThrowChoiceIsInvalidException() {
        Choice playerChoice = null;
        Choice computerChoice = PAPER;

        roundService.createRound(playerChoice, computerChoice);
    }

    @Test
    public void testComputerWon() {
        Choice playerChoice = SCISSORS;
        Choice computerChoice = ROCK;
        Round expectedRound = new Round(playerChoice, computerChoice, LOST);

        Round round = roundService.createRound(playerChoice, computerChoice);

        assertThat(round).isEqualTo(expectedRound);
    }

    @Test
    public void testDraw() {
        Choice playerChoice = SCISSORS;
        Choice computerChoice = SCISSORS;
        Round expectedRound = new Round(playerChoice, computerChoice, DRAW);

        Round round = roundService.createRound(playerChoice, computerChoice);

        assertThat(round).isEqualTo(expectedRound);
    }

    @Test
    public void testRockBeatsScissors() {
        Choice playerChoice = ROCK;
        Choice computerChoice = SCISSORS;
        Round expectedRound = new Round(playerChoice, computerChoice, WON);

        Round round = roundService.createRound(playerChoice, computerChoice);

        assertThat(round).isEqualTo(expectedRound);
    }

    @Test
    public void testScissorsBeatsPaper() {
        Choice playerChoice = SCISSORS;
        Choice computerChoice = PAPER;
        Round expectedRound = new Round(playerChoice, computerChoice, WON);

        Round round = roundService.createRound(playerChoice, computerChoice);

        assertThat(round).isEqualTo(expectedRound);
    }

    @Test
    public void testPaperBeatsRock() {
        Choice playerChoice = PAPER;
        Choice computerChoice = ROCK;
        Round expectedRound = new Round(playerChoice, computerChoice, WON);

        Round round = roundService.createRound(playerChoice, computerChoice);

        assertThat(round).isEqualTo(expectedRound);
    }
}