package com.rafaelfiume.tictactoe;

import static java.lang.System.lineSeparator;

public class ConsoleGameRenderer {

    public String render(Board gameSnapshot) {
        final ConsoleTicTacToePrinter print = new ConsoleTicTacToePrinter(gameSnapshot);
        return String.format(
                "%s" + lineSeparator() +
                "%s" + lineSeparator() + lineSeparator() +
                "%s",
                print.gameDescription(), print.board(), print.gameStatus());
    }

}
