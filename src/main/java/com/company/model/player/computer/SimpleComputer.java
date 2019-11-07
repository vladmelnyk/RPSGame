package com.company.model.player.computer;

import java.util.Random;

public class SimpleComputer extends Computer {

    public SimpleComputer(String name, Random random) {
        super(name, random);
    }

    public SimpleComputer(Random random) {
        super(random);
    }
}
