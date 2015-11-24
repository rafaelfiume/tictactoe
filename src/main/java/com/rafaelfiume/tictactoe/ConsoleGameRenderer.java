package com.rafaelfiume.tictactoe;

public class ConsoleGameRenderer {

    private final String content = "%s\n%s\n%s";

    public String render(Board gameSnapshot) {
        final ConsoleGameModel model = new ConsoleGameModel(gameSnapshot);
        return String.format(content, model.gameDescription(), model.board(), model.gameStatus());
    }

}
