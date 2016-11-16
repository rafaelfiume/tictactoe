package com.rafaelfiume.tictactoe.console

import com.rafaelfiume.tictactoe.Game
import com.rafaelfiume.tictactoe.GameRenderer
import com.rafaelfiume.tictactoe.console.ConsoleGamePrinterState.printerFor
import java.lang.System.lineSeparator

class ConsoleGameRenderer : GameRenderer {

    override fun show(game: Game): String {
        val printerState = printerFor(game)

        val ui = lineSeparator() +
                "${printerState.gameDescription()}${lineSeparator()}${lineSeparator()}" +
                "${printerState.board()}${lineSeparator()}${lineSeparator()}" +
                printerState.gameStatus()

        print(ui)
        return ui
    }
}