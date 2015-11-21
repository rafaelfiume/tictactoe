package com.rafaelfiume.tictactoe;

public enum Player {

    X('X'), O('O');

    private final char symbol;

    Player(char symbol) {
        this.symbol = symbol;
    }

    public char symbol() {
        return symbol;
    }
}
