package com.rafaelfiume.tictactoe;

import static java.lang.System.lineSeparator;

public class ConsoleGameRenderer {

    public String render(Board gameSnapshot) {
        final ConsoleGameModel model = new ConsoleGameModel(gameSnapshot);
        return String.format(
                "%s" + lineSeparator() +
                "%s" + lineSeparator() + lineSeparator() +
                "%s",
                model.gameDescription(), model.board(), model.gameStatus());
    }

}
