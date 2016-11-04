package com.rafaelfiume.tictactoe

import com.rafaelfiume.tictactoe.BoardPosition.TOP_LEFT
import com.rafaelfiume.tictactoe.BoardPosition.UNKNOWN
import com.rafaelfiume.tictactoe.support.TestingBoards.whenPlayerWinsWithA_DiagonalRow_InThe
import com.rafaelfiume.tictactoe.support.TestingBoards.whenPlayerWinsWithA_VerticalRow_InThe
import com.rafaelfiume.tictactoe.support.TestingBoards.whenPlayerWinsWithAn_HorizontalRow_InThe
import org.hamcrest.Matchers.`is`
import org.junit.Assert.assertThat
import org.junit.Assert.assertTrue
import org.junit.Test
import kotlin.test.assertFalse

class TttBoardTest {

    @Test
    fun markPositionWhenItIsAvailable() {
        val board = givenABoard()

        val marked = board.markPositionIfAvailable(TOP_LEFT, Player.O)

        assertTrue(marked)
        assertThat(board.topLeft(), `is`(Player.O.symbol()))
    }

    @Test
    fun doesNotMarkPositionWhenPlayerAnAlreadyMarkedOne() {
        val board = givenABoard()
        board.markPositionIfAvailable(TOP_LEFT, Player.O)

        val marked = board.markPositionIfAvailable(TOP_LEFT, Player.X)

        assertThat(board.topLeft(), `is`(Player.O.symbol()))
        assertFalse(marked)
    }

    @Test
    fun doesNotMarkPositionWhenPlayerChoosesUnknown() {
        val board = givenABoard()

        val marked = board.markPositionIfAvailable(UNKNOWN, Player.O)

        assertFalse(marked)
    }

    //
    // Wins
    //

    @Test
    fun nobodyWinsWhileGameIsInProgress() {
        val board = givenABoard()

        board.markPositionIfAvailable(UNKNOWN, Player.O)

        assertFalse(board.hasWinner())
    }

    @Test
    fun playerWinsWithAVerticalRow() {
        val board = givenABoard()

        whenPlayerWinsWithA_VerticalRow_InThe(board)

        assertTrue(board.hasWinner())
    }

    @Test
    fun playerWinsWithAnHorizontalRow() {
        val board = givenABoard()

        whenPlayerWinsWithAn_HorizontalRow_InThe(board)

        assertTrue(board.hasWinner())
    }

    @Test
    fun playerWinsWithADiagonalRow() {
        val board = givenABoard()

        whenPlayerWinsWithA_DiagonalRow_InThe(board)

        assertTrue(board.hasWinner())
    }

    private fun givenABoard() = TttBoard()
}