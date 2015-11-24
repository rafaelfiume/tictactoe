package com.rafaelfiume.tictactoe;

public class ConsoleGameRunner {

    private final ConsoleInputReader input;

    private final ConsoleGameRenderer renderer;

    private final Board board;

    public ConsoleGameRunner(Board board, ConsoleInputReader input, ConsoleGameRenderer renderer) {
        this.board = board;
        this.input = input;
        this.renderer = renderer;
    }

    public void start() {
        System.out.println(renderer.render(board.currentGameSnapshot()));

        while(board.gameIsRunning()) {
            board.playerChooses(input.readUserInput());
            System.out.println(renderer.render(board.currentGameSnapshot()));
        }
    }

    public static void main(String... args) {
        final Board board = new Board();

        new ConsoleGameRunner(
                board,
                new BlockingConsoleInputReader(),
                new ConsoleGameRenderer())
        .start();
    }

}
