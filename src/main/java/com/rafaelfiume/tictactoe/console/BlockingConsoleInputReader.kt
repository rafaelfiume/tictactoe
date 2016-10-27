package com.rafaelfiume.tictactoe.console

import com.rafaelfiume.tictactoe.BoardPosition

import java.util.Scanner

class BlockingConsoleInputReader : ConsoleInputReader {

    private val scanner = Scanner(System.`in`)

    override fun readUserInput(): BoardPosition = BoardPosition.of(scanner.nextInt())

}
