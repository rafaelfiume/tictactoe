package com.rafaelfiume.tictactoe;

public class ConsoleGameModel {

    private final String emtyBoard = "" +
            "   |   |   \n" +
            "---+---+---\n" +
            "   |   |   \n" +
            "---+---+---\n" +
            "   |   |   \n";

    public String gameDescription() {
        return "Game Board Creation...";
    }

    public String board() {
        return emtyBoard;
    }

    public String gameStatus() {
        return "Board Created.\n" +
                "The game will start with Player X";
    }
}
