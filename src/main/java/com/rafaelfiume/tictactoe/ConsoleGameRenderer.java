package com.rafaelfiume.tictactoe;

public class ConsoleGameRenderer {

    private final Board board;

    private final String content = "%s\n%s\n%s";

    public ConsoleGameRenderer(Board board) {
        this.board = board;
    }

    public String render() {
        final ConsoleGameModel model = new ConsoleGameModel(board.currentGameSnapshot());
        return String.format(content, model.gameDescription(), model.board(), model.gameStatus());
    }

}
