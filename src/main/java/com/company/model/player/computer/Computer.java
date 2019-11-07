package com.company.model.player.computer;

import com.company.model.enumeration.Choice;
import com.company.model.player.Player;

import java.util.Random;

// Could be further extended with easy/medium/hard levels of AI
abstract public class Computer extends Player {
    private static final String COMPUTER_NAME = "COMPUTER";
    private Random random;


    public Computer(String name, Random random) {
        super(name);
        this.random = random;
    }

    public Computer(Random random) {
        this.name = COMPUTER_NAME;
        this.random = random;
    }

    public Choice selectChoice() {
        int randomInt = random.nextInt(Choice.values().length);
        return Choice.values()[randomInt];
    }
}
