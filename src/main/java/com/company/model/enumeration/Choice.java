package com.company.model.enumeration;

public enum Choice {
    ROCK,
    PAPER,
    SCISSORS;

    public boolean beats(Choice other) {
        switch (this) {
            case ROCK:
                return other == SCISSORS;
            case PAPER:
                return other == ROCK;
            case SCISSORS:
                return other == PAPER;
            default:
                return false;
        }
    }
}
