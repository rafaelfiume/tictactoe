package com.rafaelfiume.tictactoe.console

import com.rafaelfiume.tictactoe.Game
import com.rafaelfiume.tictactoe.GameRenderer
import com.rafaelfiume.tictactoe.TttPrinter

class ConsoleGameRenderer : GameRenderer {

    override fun show(game: Game): String {
        val print = TttPrinter(game)
        val ui = "${System.lineSeparator()}" +
                "${print.gameDescription()}" + "${System.lineSeparator()}${System.lineSeparator()}" +
                "${print.board()}" + "${System.lineSeparator()}${System.lineSeparator()}" +
                "${print.gameStatus()}"

        print(ui)
        return ui
    }
}