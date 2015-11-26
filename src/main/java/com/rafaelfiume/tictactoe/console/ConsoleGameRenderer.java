package com.rafaelfiume.tictactoe.console;

import com.rafaelfiume.tictactoe.Board;
import com.rafaelfiume.tictactoe.TicTacToePrinter;

import static java.lang.System.lineSeparator;

public class ConsoleGameRenderer {

    public String render(Board gameSnapshot) {
        final TicTacToePrinter print = new TicTacToePrinter(gameSnapshot);
        return String.format(
                "%s" + lineSeparator() +
                "%s" + lineSeparator() + lineSeparator() +
                "%s",
                print.gameDescription(), print.board(), print.gameStatus());
    }

}
