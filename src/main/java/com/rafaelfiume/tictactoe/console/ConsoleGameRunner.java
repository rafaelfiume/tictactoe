package com.rafaelfiume.tictactoe.console;

import com.rafaelfiume.tictactoe.Board;

public class ConsoleGameRunner {

    private static final String BOT_MODE = "botmode";
    private static final int TURN_DURATION_WHEN_IN_BOT_MODE_IN_SECONDS = 2;

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
        final ConsoleInputReader input = (isBotModeIn(args))
                ? new BotConsoleInputReader(TURN_DURATION_WHEN_IN_BOT_MODE_IN_SECONDS)
                : new BlockingConsoleInputReader();

        new ConsoleGameRunner(new Board(), input, new ConsoleGameRenderer()).start();
    }

    private static boolean isBotModeIn(String[] args) {
        return (args.length >= 1 && BOT_MODE.equals(args[0]));
    }


}
