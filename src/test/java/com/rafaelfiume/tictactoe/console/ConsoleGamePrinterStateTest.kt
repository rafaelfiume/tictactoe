package com.rafaelfiume.tictactoe.console

import com.rafaelfiume.tictactoe.BoardPosition.TOP_RIGHT
import com.rafaelfiume.tictactoe.BoardPosition.UNKNOWN
import com.rafaelfiume.tictactoe.TttGame.Factory.newGame
import com.rafaelfiume.tictactoe.console.ConsoleGamePrinterState.printerFor
import com.rafaelfiume.tictactoe.matchers.BoardMatcher.Companion.showsABoardLike
import com.rafaelfiume.tictactoe.matchers.EmptyBoardMatcher.Companion.anEmptyBoard
import com.rafaelfiume.tictactoe.support.GameBuilder.Factory.aBoardWithPlayerXWinningWithVerticalLineOnTheLeft
import com.rafaelfiume.tictactoe.support.GameBuilder.Factory.aGameEndingWithNoWinner
import com.rafaelfiume.tictactoe.support.GameBuilder.Factory.aGameWithPlayer_O_WinningWithAnHorizontalLineOnTheBottom
import org.hamcrest.Matchers.`is`
import org.junit.Assert.assertThat
import org.junit.Test
import java.lang.System.lineSeparator

class ConsoleGamePrinterStateTest {

    @Test
    fun printEmptyBoardWhenThereAreNoMovesYet() {
        val emptyGame = newGame()

        val print = printerFor(emptyGame)

        assertThat(print.gameDescription(), `is`("Game Board Creation..."))
        assertThat(print.board(), `is`(anEmptyBoard()))
        assertThat(print.gameStatus(), `is`("The game will start with Player X" + lineSeparator() + "Choose position:"))
    }

    @Test
    fun printPlayerXWinsWhenHeGetsThreeInAVerticalRow() {
        val playerXWon = aBoardWithPlayerXWinningWithVerticalLineOnTheLeft()

        val print = printerFor(playerXWon)

        assertThat(print.gameDescription(), `is`("Player X:"))
        assertThat(print.board(), showsABoardLike(
                        " X |   |   " + lineSeparator() +
                        "---+---+---" + lineSeparator() +
                        " X | O | O " + lineSeparator() +
                        "---+---+---" + lineSeparator() +
                        " X |   |   "))
        assertThat(print.gameStatus(), `is`("PLAYER X WON!"))
    }

    @Test
    fun printPlayerOWinsWhenSheStrikesHorizontalVictory() {
        val playerOWon = aGameWithPlayer_O_WinningWithAnHorizontalLineOnTheBottom()

        val print = printerFor(playerOWon)

        assertThat(print.gameDescription(), `is`("Player O:"))
        assertThat(print.board(), showsABoardLike(
                        "   |   | X " + lineSeparator() +
                        "---+---+---" + lineSeparator() +
                        "   | X | X " + lineSeparator() +
                        "---+---+---" + lineSeparator() +
                        " O | O | O "))
        assertThat(print.gameStatus(), `is`("PLAYER O WON!"))
    }

    @Test
    fun printGameEndsWithDrawWhenThereAreNoMoreTurnsAndNoWinners() {
        val draw = aGameEndingWithNoWinner()

        val print = printerFor(draw)

        assertThat(print.gameDescription(), `is`("No More Turns Left :-)"))
        assertThat(print.board(), showsABoardLike(
                        " X | O | X " + lineSeparator() +
                        "---+---+---" + lineSeparator() +
                        " X | O | X " + lineSeparator() +
                        "---+---+---" + lineSeparator() +
                        " O | X | O "))
        assertThat(print.gameStatus(), `is`("GAME ENDS WITH A DRAW!"))
    }

    @Test
    fun printPlayerHasToChooseAnotherCellInTheBoardWhenSheTriesToMarkAnOccupiedSpaceInTheBoard() {
        val boardWithDisputedCell = newGame()
        boardWithDisputedCell.playerChooses(TOP_RIGHT)
        boardWithDisputedCell.playerChooses(TOP_RIGHT)

        val print = printerFor(boardWithDisputedCell)

        assertThat(print.gameDescription(), `is`("Player O:"))
        assertThat(print.board(), showsABoardLike(
                        "   |   | X " + lineSeparator() +
                        "---+---+---" + lineSeparator() +
                        "   |   |   " + lineSeparator() +
                        "---+---+---" + lineSeparator() +
                        "   |   |   "))
        assertThat(print.gameStatus(), `is`("Choose position:"))
    }

    @Test
    fun printPlayerHasToChooseAnotherCellInTheBoardWhenHePicksUpAnUnknownOne() {
        val boardWithDisputedCell = newGame()
        boardWithDisputedCell.playerChooses(UNKNOWN)

        val print = printerFor(boardWithDisputedCell)

        assertThat(print.gameDescription(), `is`("Player X:"))
        assertThat(print.board(), showsABoardLike(
                        "   |   |   " + lineSeparator() +
                        "---+---+---" + lineSeparator() +
                        "   |   |   " + lineSeparator() +
                        "---+---+---" + lineSeparator() +
                        "   |   |   "))
        assertThat(print.gameStatus(), `is`("Choose position:"))
    }
}
