package com.rafaelfiume.tictactoe;

import java.io.Console;
import java.util.Scanner;

public class ConsoleGameRunner {

    private final ConsoleGameRenderer renderer;

//    private final Console console = System.console();
    private final Scanner scanner = new Scanner(System.in);

    public ConsoleGameRunner(ConsoleGameRenderer renderer) {
        this.renderer = renderer;
    }

    public void start() {
//        console.printf(renderer.render());
        System.out.println(renderer.render());
    }

    public static void main(String... args) {
        new ConsoleGameRunner(new ConsoleGameRenderer()).start();
    }

}
