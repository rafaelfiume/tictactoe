package com.rafaelfiume.tictactoe

import com.rafaelfiume.tictactoe.support.BoardBuilder
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher
import org.junit.Test

import com.rafaelfiume.tictactoe.BoardPosition.*
import com.rafaelfiume.tictactoe.BoardTest.DrawMatcher.Companion.hasDraw
import com.rafaelfiume.tictactoe.BoardTest.PlayerWonMatcher.Companion.hasWinner
import com.rafaelfiume.tictactoe.support.BoardBuilder.aGameEndingWithNoWinner
import com.rafaelfiume.tictactoe.support.BoardBuilder.aGameWithPlayer_O_WinningWithAnHorizontalLineOnTheBottom
import com.rafaelfiume.tictactoe.support.BoardBuilder.emptyBoard
import java.lang.String.format
import org.hamcrest.Matchers.`is`
import org.junit.Assert.*

class BoardTest {

    @Test
    fun gameIsNotOverIfThereIsNoWinnerOrDraw() {
        val game = BoardBuilder().withPlayerXChoosing(CENTER).build()

        assertTrue(game.gameIsOn())
    }

    // Winning

    @Test
    fun playerWinsWithVerticalLineInTheMiddle() {
        val game = BoardBuilder().withPlayerXChoosing(CENTER).withPlayerOChoosing(MID_RIGHT).withPlayerXChoosing(TOP_CENTER).withPlayerOChoosing(TOP_RIGHT).withPlayerXChoosing(BOTTOM_CENTER).build()

        assertThat(game, hasWinner(Player.X))
    }

    @Test
    fun playerWinsWithVerticalLineInTheRight() {
        val game = BoardBuilder().withPlayerXChoosing(TOP_RIGHT).withPlayerOChoosing(CENTER).withPlayerXChoosing(MID_RIGHT).withPlayerOChoosing(BOTTOM_CENTER).withPlayerXChoosing(BOTTOM_RIGHT).build()

        assertThat(game, hasWinner(Player.X))
    }

    @Test
    fun playerWinsWithHorizontalLineInTheBottom() {
        assertThat(aGameWithPlayer_O_WinningWithAnHorizontalLineOnTheBottom(), hasWinner(Player.O))
    }

    @Test
    fun playerWinsWithDiagonalLine() {
        val game = BoardBuilder().withPlayerXChoosing(TOP_RIGHT).withPlayerOChoosing(TOP_CENTER).withPlayerXChoosing(CENTER).withPlayerOChoosing(BOTTOM_CENTER).withPlayerXChoosing(BOTTOM_LEFT).build()

        assertThat(game, hasWinner(Player.X))
    }

    // Draw

    @Test
    fun gameEndsInADrawWhenPlayersCantGetThreeInARow() {
        assertThat(aGameEndingWithNoWinner(), hasDraw())
    }

    // Validation

    @Test
    fun doNotChangeTurnWhenPlayerTriesToMarkAnAlreadyOccupiedCellInTheBoard() {// again, this regards turns not board (needs refactor to improve the design)
        // given
        val board = emptyBoard()
        assertThat(board.currentTurn(), `is`(1))
        assertThat(board.currentPlayer(), `is`(Player.X))

        board.playerChooses(CENTER)
        assertThat(board.currentTurn(), `is`(2))
        assertThat(board.currentPlayer(), `is`(Player.O))

        // when
        board.playerChooses(CENTER)

        // then
        assertThat(board.currentPlayer(), `is`(Player.O))
        assertThat(board.currentTurn(), `is`(2))
    }

    @Test
    fun doNotChangeTurnWhenPlayerChoosesUnknownCellInTheBoard() {
        // given
        val board = emptyBoard()
        assertThat(board.currentTurn(), `is`(1))
        assertThat(board.currentPlayer(), `is`(Player.X))

        // when
        board.playerChooses(UNKNOWN)

        // then
        assertThat(board.currentTurn(), `is`(1))
        assertThat(board.currentPlayer(), `is`(Player.X))
    }

    //
    //// MATCHER
    //

    internal class PlayerWonMatcher private constructor(
            private val expectedWinner: Player) : GameResultMatcher(true, false) {

        override fun matchesSafely(resultBoard: Board): Boolean {
            return super.matchesSafely(resultBoard) && resultBoard.winner() === expectedWinner
        }

        override fun describeTo(description: Description) {
            description.appendText(boardDescriptionWithWinner(true, false, expectedWinner))
        }

        override fun describeMismatchSafely(actual: Board, mismatchDescription: Description) {
            mismatchDescription.appendText(boardDescriptionWithWinner(actual.hasWinner(), actual.hasDraw(), actual.winner()))
        }

        private fun boardDescriptionWithWinner(winner: Boolean, draw: Boolean, expectedWinner: Player) =
                super.boardDescription(winner, draw) + "; winner is $expectedWinner"

        companion object {
            fun hasWinner(winner: Player): Matcher<in Board> = PlayerWonMatcher(winner)
        }
    }

    internal class DrawMatcher private constructor() : GameResultMatcher(false, true) {
        companion object {
            fun hasDraw(): Matcher<in Board> = DrawMatcher()
        }
    }

    internal abstract class GameResultMatcher internal constructor(
            private val expectsGameEndingWithAWinner: Boolean,
            private val expectsGameEndingWithADraw: Boolean) : TypeSafeMatcher<Board>() {

        override fun matchesSafely(resultBoard: Board): Boolean {
            return !resultBoard.gameIsOn()
                    && resultBoard.hasWinner() === expectsGameEndingWithAWinner
                    && resultBoard.hasDraw() === expectsGameEndingWithADraw
        }

        override fun describeTo(description: Description) {
            description.appendText(boardDescription(expectsGameEndingWithAWinner, expectsGameEndingWithADraw))
        }

        override fun describeMismatchSafely(actual: Board, mismatchDescription: Description) {
            mismatchDescription.appendText(boardDescription(actual.hasWinner(), actual.hasDraw()))
        }

        protected fun boardDescription(winner: Boolean, draw: Boolean) = "game: has a winner: $winner; has a draw: $draw"
    }

}
