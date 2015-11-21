package com.rafaelfiume.tictactoe.support;

import com.rafaelfiume.tictactoe.ConsoleInputReader;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class ConsoleInputReaderStub implements ConsoleInputReader {

    private final Queue<Integer> positions = new ArrayBlockingQueue(10);

    @Override
    public int readUserInput() {
        return positions.poll();
    }

    public void willReturn(int position) {
        positions.add(position);
    }
}
