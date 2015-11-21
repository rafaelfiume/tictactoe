package com.rafaelfiume.tictactoe;

public class ConsoleGameRunner {

    private final ConsoleInput input;

    private final ConsoleGameRenderer renderer;

    private final Board board;

    public ConsoleGameRunner(Board board, ConsoleInput input, ConsoleGameRenderer renderer) {
        this.board = board;
        this.input = input;
        this.renderer = renderer;
    }

    public void start() {
        System.out.println(renderer.render());
        while(!board.isGameOver()) {
            input.read();
            System.out.println(renderer.render());
        }
    }

    public static void main(String... args) {
        final Board board = new Board();

        new ConsoleGameRunner(
                board, new ConsoleInput(new BlockingConsoleInputReader(), board),
                new ConsoleGameRenderer(board))
        .start();
    }

}
