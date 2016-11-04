package com.rafaelfiume.tictactoe

import java.lang.String.format
import java.lang.System.lineSeparator

class TttPrinter(game: Game) {

    private val board: Board
    private val printerState: PrinterState

    private val boardTemplate = "" +
            " %s | %s | %s " + lineSeparator() +
            "---+---+---"    + lineSeparator() +
            " %s | %s | %s " + lineSeparator() +
            "---+---+---"    + lineSeparator() +
            " %s | %s | %s "

    init {
        this.board = game.board()
        this.printerState = printerStateFrom(game)
    }

    fun gameDescription() = printerState.gameDescription()

    fun board(): String {
        return format(boardTemplate,
                topLeft()   , topCenter()   , topRight(),
                midLeft()   , center()      , midRight(),
                bottomLeft(), bottomCenter(), bottomRight())
    }

    fun gameStatus() = printerState.gameStatus()

    private fun printerStateFrom(game: Game): PrinterState {
        if (game.gameHasNotStarted()) return EmptyBoardState()
        if (game.gameIsOn())          return PlayerTurnState(game.currentPlayer())
        if (game.hasWinner())         return PlayerWonState(game.winner())
        if (game.hasDraw())           return DrawState()

        throw IllegalStateException("Unknown game state")
    }

    private interface PrinterState {
        fun gameDescription(): String
        fun gameStatus(): String
    }

    private inner class EmptyBoardState : PrinterState {

        override fun gameDescription() = "Game Board Creation..."
        override fun gameStatus() =
             "Board Created." + lineSeparator() +
             "The game will start with Player X" + lineSeparator() +
             CHOOSE_POSITION
    }

    private inner class PlayerTurnState internal constructor(private val player: Player) : PrinterState {

        override fun gameDescription() = "Player $player:"
        override fun gameStatus() = CHOOSE_POSITION
    }

    private inner class PlayerWonState internal constructor(private val winner: Player) : PrinterState {

        override fun gameDescription() = "Player $winner:"
        override fun gameStatus() = "PLAYER $winner WON!"
    }

    private inner class DrawState : PrinterState {

        override fun gameDescription() = "No More Turns Left :-)"
        override fun gameStatus() = "GAME ENDS WITH A DRAW!"
    }

    //
    // Boring methods
    //

    private fun topLeft()      = markedAt(board.topLeft())
    private fun topCenter()    = markedAt(board.topCenter())
    private fun topRight()     = markedAt(board.topRight())
    private fun midLeft()      = markedAt(board.midLeft())
    private fun center()       = markedAt(board.center())
    private fun midRight()     = markedAt(board.midRight())
    private fun bottomLeft()   = markedAt(board.bottomLeft())
    private fun bottomCenter() = markedAt(board.bottomCenter())
    private fun bottomRight()  = markedAt(board.bottomRight())

    private fun markedAt(c: Char) = if (isEmpty(c)) BLANK else Character.toString(c)

    private fun isEmpty(c: Char) = !Character.isLetter(c)

    companion object {

        private val BLANK = " "
        private val CHOOSE_POSITION = "Choose Position:"
    }

}
