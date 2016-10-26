package com.rafaelfiume.tictactoe.console;

import com.rafaelfiume.tictactoe.BoardPosition;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BotConsoleInputReader implements ConsoleInputReader {

    private final int turnDurationInSeconds;

    private final RandomPositionGenerator positionGenerator = new RandomPositionGenerator();

    public BotConsoleInputReader(int turnDurationInSeconds) {
        this.turnDurationInSeconds = turnDurationInSeconds * 1000;
    }

    @Override
    public BoardPosition readUserInput() {
        waitTillTurnEnds();
        return BoardPosition.of(positionGenerator.next());
    }

    private void waitTillTurnEnds() {
        try {
            Thread.sleep(turnDurationInSeconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static class RandomPositionGenerator {

        private static final int MAX_POSITION = 9;

        private final List<Integer> availablePositions = new ArrayList<>();

        private int cursor = 0;

        RandomPositionGenerator() {
            for (int i = 1; i <= MAX_POSITION; i++) {
                availablePositions.add(i);
            }
            Collections.shuffle(availablePositions);
        }

        /*
         * Relies on the client to not exceed the limit of #cursor invocation that is equal to MAX_POSITION
         */
        int next() {
            return availablePositions.get(index());
        }

        private int index() {
            System.out.println(String.format("Current cursor: %s; current value: %s", cursor, availablePositions.get(cursor)));
            return cursor++;
        }

    }
}
