package com.rafaelfiume.tictactoe.console

import com.rafaelfiume.tictactoe.Board
import com.rafaelfiume.tictactoe.GameRenderer
import com.rafaelfiume.tictactoe.TicTacToePrinter

class ConsoleGameRenderer : GameRenderer {

    override fun show(gameSnapshot: Board): String {
        val print = TicTacToePrinter(gameSnapshot)
        val currentGame = "${System.lineSeparator()}" +
                "${print.gameDescription()}" + "${System.lineSeparator()}${System.lineSeparator()}" +
                "${print.board()}" + "${System.lineSeparator()}${System.lineSeparator()}" +
                "${print.gameStatus()}"

        print(currentGame)
        return currentGame
    }
}