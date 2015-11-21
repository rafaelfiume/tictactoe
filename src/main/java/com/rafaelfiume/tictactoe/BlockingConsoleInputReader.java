package com.rafaelfiume.tictactoe;

import java.util.Scanner;

public class BlockingConsoleInputReader implements ConsoleInputReader {

    private final Scanner scanner = new Scanner(System.in);

    @Override
    public int readUserInput() {
        return scanner.nextInt();
    }

}
