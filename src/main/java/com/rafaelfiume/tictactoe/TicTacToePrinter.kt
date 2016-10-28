package com.rafaelfiume.tictactoe

import java.lang.String.format
import java.lang.System.lineSeparator

class TicTacToePrinter(private val snapshot: Game) {

    private val printerState: PrinterState

    private val boardTemplate = "" +
            " %s | %s | %s " + lineSeparator() +
            "---+---+---" + lineSeparator() +
            " %s | %s | %s " + lineSeparator() +
            "---+---+---" + lineSeparator() +
            " %s | %s | %s "

    init {
        this.printerState = printerStateFrom(snapshot)
    }

    fun gameDescription(): String {
        return printerState.gameDescription()
    }

    fun board(): String {
        return format(boardTemplate,
                topLeft(), topCenter(), topRight(),
                midLeft(), center(), midRight(),
                bottomLeft(), bottomCenter(), bottomRight())
    }

    fun gameStatus(): String {
        return printerState.gameStatus()
    }

    private fun printerStateFrom(game: Game): PrinterState {
        if (game.gameHasNotStarted()) return EmptyBoardState()
        if (game.gameIsOn()) return PlayerTurnState(game.currentPlayer())
        if (game.hasWinner()) return PlayerWonState(game.winner())
        if (game.hasDraw()) return DrawState()

        throw IllegalStateException("Unknown game state")
    }

    private interface PrinterState {
        fun gameDescription(): String
        fun gameStatus(): String
    }

    private inner class EmptyBoardState : PrinterState {

        override fun gameDescription(): String {
            return "Game Board Creation..."
        }

        override fun gameStatus(): String {
            return "Board Created." + lineSeparator() +
                    "The game will start with Player X" + lineSeparator() +
                    CHOOSE_POSITION
        }
    }

    private inner class PlayerTurnState internal constructor(private val player: Player) : PrinterState {

        override fun gameDescription(): String {
            return format("Player %s:", player)
        }

        override fun gameStatus(): String {
            return CHOOSE_POSITION
        }
    }

    private inner class PlayerWonState internal constructor(private val winner: Player) : PrinterState {

        override fun gameDescription(): String {
            return format("Player %s:", winner)
        }

        override fun gameStatus(): String {
            return format("PLAYER %s WON!", winner)
        }
    }

    private inner class DrawState : PrinterState {

        override fun gameDescription(): String {
            return "No More Turns Left :-)"
        }

        override fun gameStatus(): String {
            return "GAME ENDS WITH A DRAW!"
        }
    }

    //
    // Boring methods
    //

    private fun topLeft(): String {
        return markAt(snapshot.topLeft())
    }

    private fun topCenter(): String {
        return markAt(snapshot.topCenter())
    }

    private fun topRight(): String {
        return markAt(snapshot.topRight())
    }

    private fun midLeft(): String {
        return markAt(snapshot.midLeft())
    }

    private fun center(): String {
        return markAt(snapshot.center())
    }

    private fun midRight(): String {
        return markAt(snapshot.midRight())
    }

    private fun bottomLeft(): String {
        return markAt(snapshot.bottomLeft())
    }

    private fun bottomCenter(): String {
        return markAt(snapshot.bottomCenter())
    }

    private fun bottomRight(): String {
        return markAt(snapshot.bottomRight())
    }

    private fun markAt(c: Char): String {
        return if (isEmpty(c)) BLANK else Character.toString(c)
    }

    private fun isEmpty(c: Char): Boolean {
        return !Character.isLetter(c)
    }

    companion object {

        private val BLANK = " "
        private val CHOOSE_POSITION = "Choose Position:"
    }

}
