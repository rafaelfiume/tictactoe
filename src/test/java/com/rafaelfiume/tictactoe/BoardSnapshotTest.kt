package com.rafaelfiume.tictactoe

import com.rafaelfiume.tictactoe.BoardPosition.*
import com.rafaelfiume.tictactoe.support.BoardBuilder.Companion.aBoard
import org.hamcrest.Matchers.`is`
import org.junit.Assert.*
import org.junit.Test

class BoardSnapshotTest {

    @Test
    fun boardReturnsASnapshotOfTheCurrentGame() {
        val snapshot = aBoard()
                .withPlayerXChoosing(TOP_RIGHT)
                .withPlayerOChoosing(CENTER)
                .withPlayerXChoosing(MID_RIGHT)
                .withPlayerOChoosing(BOTTOM_CENTER)
                .withPlayerXChoosing(BOTTOM_RIGHT).build().snapshot()

        assertFalse("snapshot should say that game is over", snapshot.gameIsOn())
        assertThat(snapshot.winner(), `is`(Player.X))
        assertFalse(snapshot.gameHasNotStarted())
    }

    @Test
    fun changingTheBoardDoesNotReflectsOnPreviousSnapshots() {
        val original = aBoard().build()

        val snapshot = original.snapshot()
        assertTrue("snapshot should say that game is on", snapshot.gameIsOn())

        original.playerChooses(BOTTOM_RIGHT)
        assertTrue("original should not change state of a snapshot", snapshot.gameHasNotStarted())
    }

}
