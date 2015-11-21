package com.rafaelfiume.tictactoe;

public class ConsoleInput {

    private final Board board;

    private final ConsoleInputReader consoleInputReader;

    public ConsoleInput(ConsoleInputReader consoleInputReader, Board board) {
        this.board = board;
        this.consoleInputReader = consoleInputReader;
    }

    public void read() {
        board.playerChooses(BoardPosition.of(consoleInputReader.readUserInput()));
    }

}
