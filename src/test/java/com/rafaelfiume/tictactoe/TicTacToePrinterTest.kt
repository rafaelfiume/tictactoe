package com.rafaelfiume.tictactoe

import com.rafaelfiume.tictactoe.BoardPosition.TOP_RIGHT
import com.rafaelfiume.tictactoe.matchers.BoardMatcher.Companion.showsABoardLike
import com.rafaelfiume.tictactoe.matchers.EmptyBoardMatcher.Companion.anEmptyBoard
import com.rafaelfiume.tictactoe.support.BoardBuilder.Companion.aBoard
import com.rafaelfiume.tictactoe.support.BoardBuilder.Companion.aBoardWithPlayerXWinningWithVerticalLineOnTheLeft
import com.rafaelfiume.tictactoe.support.BoardBuilder.Companion.aGameEndingWithNoWinner
import com.rafaelfiume.tictactoe.support.BoardBuilder.Companion.aGameWithPlayer_O_WinningWithAnHorizontalLineOnTheBottom
import com.rafaelfiume.tictactoe.support.BoardBuilder.Companion.emptyBoard
import org.hamcrest.Matchers.`is`
import org.junit.Assert.assertThat
import org.junit.Test
import java.lang.System.lineSeparator

class TicTacToePrinterTest {

    @Test
    fun printEmptyBoardWhenThereAreNoMovesYet() {
        val emptyGame = emptyBoard()

        val print = TicTacToePrinter(emptyGame)

        assertThat(print.gameDescription(), `is`("Game Board Creation..."))
        assertThat(print.board(), `is`(anEmptyBoard()))
        assertThat(print.gameStatus(), `is`("Board Created." + lineSeparator() + "The game will start with Player X" + lineSeparator() + "Choose Position:"))
    }

    @Test
    fun printPlayerXWinsWhenHeGetsThreeInAVerticalRow() {
        val playerXWon = aBoardWithPlayerXWinningWithVerticalLineOnTheLeft()

        val print = TicTacToePrinter(playerXWon)

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

        val print = TicTacToePrinter(playerOWon)

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

        val print = TicTacToePrinter(draw)

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
        val boardWithDisputedCell = aBoard().withPlayerXChoosing(TOP_RIGHT).withPlayerOChoosing(TOP_RIGHT).build()

        val print = TicTacToePrinter(boardWithDisputedCell)

        assertThat(print.gameDescription(), `is`("Player O:"))
        assertThat(print.board(), showsABoardLike(
                        "   |   | X " + lineSeparator() +
                        "---+---+---" + lineSeparator() +
                        "   |   |   " + lineSeparator() +
                        "---+---+---" + lineSeparator() +
                        "   |   |   "))
        assertThat(print.gameStatus(), `is`("Choose Position:"))
    }

    @Test
    fun printPlayerHasToChooseAnotherCellInTheBoardWhenHePicksUpAnUnknownOne() {
        val board = aBoard().withPlayerXChoosingAnUnknownCell().build().snapshot()

        val print = TicTacToePrinter(board)

        assertThat(print.gameDescription(), `is`("Player X:"))
        assertThat(print.board(), showsABoardLike(
                        "   |   |   " + lineSeparator() +
                        "---+---+---" + lineSeparator() +
                        "   |   |   " + lineSeparator() +
                        "---+---+---" + lineSeparator() +
                        "   |   |   "))
        assertThat(print.gameStatus(), `is`("Choose Position:"))
    }
}
