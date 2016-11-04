package com.rafaelfiume.tictactoe.console

import com.rafaelfiume.tictactoe.Game
import com.rafaelfiume.tictactoe.GameRenderer
import com.rafaelfiume.tictactoe.console.ConsoleGamePrinterState.printerFor

class ConsoleGameRenderer : GameRenderer {

    override fun show(game: Game): String {
        val printerState = printerFor(game)

        val ui = "${System.lineSeparator()}" +
                "${printerState.gameDescription()}" + "${System.lineSeparator()}${System.lineSeparator()}" +
                "${printerState.board()}" + "${System.lineSeparator()}${System.lineSeparator()}" +
                "${printerState.gameStatus()}"

        print(ui)
        return ui
    }
}