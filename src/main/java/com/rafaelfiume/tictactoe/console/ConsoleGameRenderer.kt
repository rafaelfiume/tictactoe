package com.rafaelfiume.tictactoe.console

import com.rafaelfiume.tictactoe.Board
import com.rafaelfiume.tictactoe.GameRenderer
import com.rafaelfiume.tictactoe.TicTacToePrinter

class ConsoleGameRenderer : GameRenderer {

    override fun render(gameSnapshot: Board): String {
        val print = TicTacToePrinter(gameSnapshot)
        return "${print.gameDescription()}" + "${System.lineSeparator()}" +
                "${print.board()}"           + "${System.lineSeparator()} ${System.lineSeparator()}" +
                "${print.gameStatus()}"
    }
}