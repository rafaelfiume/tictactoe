package com.rafaelfiume.tictactoe.support;

import com.rafaelfiume.tictactoe.BoardPosition;
import com.rafaelfiume.tictactoe.console.ConsoleInputReader;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class ConsoleInputReaderStub implements ConsoleInputReader {

    private final Queue<Integer> positions = new ArrayBlockingQueue(10);

    @Override
    public BoardPosition readUserInput() {
        return BoardPosition.of(positions.poll());
    }

    public void willReturn(int position) {
        positions.add(position);
    }

}
