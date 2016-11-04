package com.rafaelfiume.tictactoe

import com.rafaelfiume.tictactoe.support.TestingBoards.whenPlayerWinsWithA_VerticalRow_InThe
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class SnapshotBoardTest {

    @Test
    fun changingTheBoardDoesNotReflectsOnPreviousSnapshots() {
        val board = TttBoard()
        val firstSnapshot = board.snapshot()
        assertFalse(firstSnapshot.hasWinner())

        whenPlayerWinsWithA_VerticalRow_InThe(board)

        assertFalse("original should not change state of a previous snapshot", firstSnapshot.hasWinner())
        assertTrue(board.snapshot().hasWinner())
    }

}
