package com.rafaelfiume.tictactoe

import com.rafaelfiume.tictactoe.BoardPosition.TOP_RIGHT
import com.rafaelfiume.tictactoe.TttGameTest.DrawMatcher.Companion.hasDraw
import com.rafaelfiume.tictactoe.TttGameTest.PlayerWonMatcher.Companion.hasWinner
import com.rafaelfiume.tictactoe.support.TestingBoards.givenABoard
import com.rafaelfiume.tictactoe.support.TestingTurns.givenATurn
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.`is`
import org.hamcrest.TypeSafeMatcher
import org.junit.Assert.*
import org.junit.Test
import org.mockito.BDDMockito.`when`
import org.mockito.BDDMockito.given

class TttGameTest {

    val turn = givenATurn()
    val board = givenABoard()

    @Test
    fun returnsCurrentPlayer() {
        val subject = TttGame(board, turn)
        given(turn.currentPlayer()).willReturn(Player.O)

        val currentPlayer = subject.currentPlayer()

        assertThat(currentPlayer, `is`(Player.O))
    }

    @Test
    fun gameIsOnIfThereIsNoWinnerOrDraw() {
        val subject = TttGame(board, turn)
        given(board.hasWinner()).willReturn(false)

        subject.playerChooses(TOP_RIGHT) // not being used yet here

        assertTrue(subject.gameIsOn())
    }

    @Test
    fun gameIsOverIfThereIsNoWinnerOrDraw() {
        val subject = TttGame(board, turn)
        given(board.hasWinner()).willReturn(true)

        val gameIsOn = subject.gameIsOn()

        assertFalse(gameIsOn)
    }

    // Winning

    @Test
    fun playerWinsWithVerticalLineInTheMiddle() {
        val subject = TttGame(board, turn)

        `when`(board.hasWinner()).thenReturn(true)

        assertThat(subject, hasWinner(Player.X))
    }

    // Draw

    @Test
    fun gameEndsInADraw() {
        val subject = TttGame(board, turn)

        `when`(turn.hasNoMoreTurns()).thenReturn(true)
        `when`(board.hasWinner()).thenReturn(false)

        assertThat(subject, hasDraw())
    }

    //
    //// MATCHER
    //

    @Suppress("unused") // used via companion method hasWinner()
    internal class PlayerWonMatcher private constructor(
            private val expectedWinner: Player) : GameResultMatcher(true, false) {

        override fun matchesSafely(resultBoard: Game): Boolean {
            return super.matchesSafely(resultBoard) && resultBoard.winner() === expectedWinner
        }

        override fun describeTo(description: Description) {
            description.appendText(gameDescriptionWithWinner(true, false, expectedWinner))
        }

        override fun describeMismatchSafely(actual: Game, mismatchDescription: Description) {
            mismatchDescription.appendText(gameDescriptionWithWinner(actual.hasWinner(), actual.hasDraw(), actual.winner()))
        }

        private fun gameDescriptionWithWinner(winner: Boolean, draw: Boolean, expectedWinner: Player) =
                super.gameDescription(winner, draw) + "; winner is $expectedWinner"

        companion object {
            fun hasWinner(winner: Player): Matcher<in Game> = PlayerWonMatcher(winner)
        }
    }

    @Suppress("unused") // used via companion method hasDraw()
    internal class DrawMatcher private constructor() : GameResultMatcher(false, true) {
        companion object {
            fun hasDraw(): Matcher<in Game> = DrawMatcher()
        }
    }

    internal abstract class GameResultMatcher internal constructor(
            private val expectsGameEndingWithAWinner: Boolean,
            private val expectsGameEndingWithADraw: Boolean) : TypeSafeMatcher<Game>() {

        override fun matchesSafely(resultBoard: Game): Boolean {
            return !resultBoard.gameIsOn()
                    && resultBoard.hasWinner() === expectsGameEndingWithAWinner
                    && resultBoard.hasDraw() === expectsGameEndingWithADraw
        }

        override fun describeTo(description: Description) {
            description.appendText(gameDescription(expectsGameEndingWithAWinner, expectsGameEndingWithADraw))
        }

        override fun describeMismatchSafely(actual: Game, mismatchDescription: Description) {
            mismatchDescription.appendText(gameDescription(actual.hasWinner(), actual.hasDraw()))
        }

        protected fun gameDescription(winner: Boolean, draw: Boolean) = "game: has a winner: $winner; has a draw: $draw"
    }

}
