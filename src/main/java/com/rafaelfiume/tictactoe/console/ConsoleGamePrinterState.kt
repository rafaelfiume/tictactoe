package com.rafaelfiume.tictactoe.console

import com.rafaelfiume.tictactoe.Board
import com.rafaelfiume.tictactoe.Game
import com.rafaelfiume.tictactoe.Player
import java.lang.String.format
import java.lang.System.lineSeparator

object ConsoleGamePrinterState {

    fun printerFor(game: Game): PrinterState {
        if (game.gameHasNotStarted()) return EmptyBoardState(game.board())
        if (game.gameIsOn())          return PlayerTurnState(game.board(), game.currentPlayer())
        if (game.hasWinner())         return PlayerWonState(game.board(), game.winner())
        if (game.hasDraw())           return DrawState(game.board())

        throw IllegalStateException("Unknown game state")
    }

    private abstract class AbstractBoardPrinter(private val board: Board) : PrinterState {

        private val boardTemplate = "" +
                " %s | %s | %s " + lineSeparator() +
                "---+---+---"    + lineSeparator() +
                " %s | %s | %s " + lineSeparator() +
                "---+---+---"    + lineSeparator() +
                " %s | %s | %s "

        private val BLANK = " "
        protected val CHOOSE_POSITION = "Choose position:"

        override fun board(): String {
            return format(boardTemplate,
                    topLeft()   , topCenter()   , topRight(),
                    midLeft()   , center()      , midRight(),
                    bottomLeft(), bottomCenter(), bottomRight())
        }

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
    }

    private class EmptyBoardState(board: Board) : AbstractBoardPrinter(board) {

        override fun gameDescription() = "Game Board Creation..."
        override fun gameStatus() =
             "The game will start with Player X" + lineSeparator() + CHOOSE_POSITION
    }

    private class PlayerTurnState internal constructor(
            board: Board,
            private val player: Player) : AbstractBoardPrinter(board) {

        override fun gameDescription() = "Player $player:"
        override fun gameStatus() = CHOOSE_POSITION
    }

    private class PlayerWonState internal constructor(
            board: Board,
            private val winner: Player) : AbstractBoardPrinter(board) {

        override fun gameDescription() = "Player $winner:"
        override fun gameStatus() = "PLAYER $winner WON!"
    }

    private class DrawState(board: Board) : AbstractBoardPrinter(board) {

        override fun gameDescription() = "No More Turns Left :-)"
        override fun gameStatus() = "GAME ENDS WITH A DRAW!"
    }

}
