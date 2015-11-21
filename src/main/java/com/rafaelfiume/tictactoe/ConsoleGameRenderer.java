package com.rafaelfiume.tictactoe;

public class ConsoleGameRenderer {

    private ConsoleGameModel model = new ConsoleGameModel();

    private final String content = "%s\n%s\n%s";

    public String render() {
        return String.format(content, model.gameDescription(), model.board(), model.gameStatus());
    }

}
