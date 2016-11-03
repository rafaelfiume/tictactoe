package com.rafaelfiume.tictactoe

import com.rafaelfiume.tictactoe.BoardPosition.*
import com.rafaelfiume.tictactoe.BoardTest.DrawMatcher.Companion.hasDraw
import com.rafaelfiume.tictactoe.BoardTest.PlayerWonMatcher.Companion.hasWinner
import com.rafaelfiume.tictactoe.support.BoardBuilder.Companion.aBoard
import com.rafaelfiume.tictactoe.support.BoardBuilder.Companion.aGameEndingWithNoWinner
import com.rafaelfiume.tictactoe.support.BoardBuilder.Companion.aGameWithPlayer_O_WinningWithAnHorizontalLineOnTheBottom
import com.rafaelfiume.tictactoe.support.BoardBuilder.Companion.emptyBoard
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.`is`
import org.hamcrest.TypeSafeMatcher
import org.junit.Assert.*
import org.junit.Test
import org.mockito.BDDMockito.*
import org.mockito.Matchers.anyBoolean
import org.mockito.Mockito.never

class BoardTest {

    @Test
    fun gameIsOnIfThereIsNoWinnerOrDraw() {
        val game = aBoard()
                .withPlayerXChoosing(TOP_RIGHT).build()

        assertTrue(game.gameIsOn())
    }

    @Test
    fun gameIsOverIfThereIsNoWinnerOrDraw() {
        val game = aBoard()
                .withPlayerXChoosing(TOP_RIGHT)
                .withPlayerOChoosing(CENTER)
                .withPlayerXChoosing(MID_RIGHT)
                .withPlayerOChoosing(BOTTOM_CENTER)
                .withPlayerXChoosing(BOTTOM_RIGHT).build()

        assertFalse(game.gameIsOn())
    }

    // Winning

    @Test
    fun playerWinsWithVerticalLineInTheMiddle() {
        val game = aBoard()
                .withPlayerXChoosing(CENTER)
                .withPlayerOChoosing(MID_RIGHT)
                .withPlayerXChoosing(TOP_CENTER)
                .withPlayerOChoosing(TOP_RIGHT)
                .withPlayerXChoosing(BOTTOM_CENTER).build()

        assertThat(game, hasWinner(Player.X))
    }

    @Test
    fun playerWinsWithVerticalLineInTheRight() {
        val game = aBoard()
                .withPlayerXChoosing(TOP_RIGHT)
                .withPlayerOChoosing(CENTER)
                .withPlayerXChoosing(MID_RIGHT)
                .withPlayerOChoosing(BOTTOM_CENTER)
                .withPlayerXChoosing(BOTTOM_RIGHT).build()

        assertThat(game, hasWinner(Player.X))
    }

    @Test
    fun playerWinsWithHorizontalLineInTheBottom() {
        assertThat(aGameWithPlayer_O_WinningWithAnHorizontalLineOnTheBottom(), hasWinner(Player.O))
    }

    @Test
    fun playerWinsWithDiagonalLine() {
        val game = aBoard()
                .withPlayerXChoosing(TOP_RIGHT)
                .withPlayerOChoosing(TOP_CENTER)
                .withPlayerXChoosing(CENTER)
                .withPlayerOChoosing(BOTTOM_CENTER)
                .withPlayerXChoosing(BOTTOM_LEFT).build()

        assertThat(game, hasWinner(Player.X))
    }

    // Draw

    @Test
    fun gameEndsInADrawWhenPlayersCantGetThreeInARow() {
        assertThat(aGameEndingWithNoWinner(), hasDraw())
    }

    // Turns

    @Test
    fun changesTurnWhenPlayerMarksACellInTheBoard() {
        val turn = givenATurn()

        emptyBoard(turn).playerChooses(CENTER)

        then(turn).should(times(1)).switchTurnIf(true)
    }

    @Test
    fun doesNotChangeTurnWhenPlayerTriesToMarkAnAlreadyOccupiedCellInTheBoard() {// again, this regards turns not board (needs refactor to improve the design)
        val turn = givenATurn()
        val board = emptyBoard(turn)

        board.playerChooses(CENTER)
        board.playerChooses(CENTER)
        board.playerChooses(CENTER)

        then(turn).should(times(1)).switchTurnIf(true)
    }

    @Test
    fun doesNotChangeTurnWhenPlayerChoosesUnknownCellInTheBoard() {
        val turn = givenATurn()

        emptyBoard(turn).playerChooses(UNKNOWN)

        then(turn).should(never()).switchTurnIf(anyBoolean())
    }

    @Test
    fun returnsCurrentPlayer() {
        val turn = givenATurn()
        given(turn.currentPlayer()).willReturn(Player.O)

        val currentPlayer = emptyBoard(turn).currentPlayer()

        assertThat(currentPlayer, `is`(Player.O))
    }

    private fun givenATurn(): Turn {
        val turn = mock(Turn::class.java)
        given(turn.currentPlayer()).willReturn(Player.O)
        return turn
    }

    //
    //// MATCHER
    //

    @Suppress("unused") // Used via companion method hasWinner()
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

    @Suppress("unused") // Used via companion method hasDraw()
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
