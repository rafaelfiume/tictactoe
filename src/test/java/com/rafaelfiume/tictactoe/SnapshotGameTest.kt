package com.rafaelfiume.tictactoe

import com.rafaelfiume.tictactoe.BoardPosition.*
import com.rafaelfiume.tictactoe.support.GameBuilder.Factory.aGame
import org.hamcrest.Matchers.`is`
import org.junit.Assert.*
import org.junit.Test

class SnapshotGameTest {

    @Test
    fun returnsABoardSnapshot() {
        val game = aGame()
                .withPlayerXChoosing(TOP_RIGHT)
                .withPlayerOChoosing(CENTER)
                .withPlayerXChoosing(MID_RIGHT)
                .withPlayerOChoosing(BOTTOM_CENTER)
                .withPlayerXChoosing(BOTTOM_RIGHT).build()

        val snapshot = game.snapshot()

        assertFalse("snapshot should say that game is over", snapshot.gameIsOn())
        assertThat(snapshot.winner(), `is`(Player.X))
        assertFalse(snapshot.gameHasNotStarted())
    }

    @Test
    fun changingTheBoardDoesNotReflectsOnPreviousSnapshots() {
        val game = aGame().build()
        val firstSnapshot = game.snapshot()
        assertTrue("game should say that game is on", firstSnapshot.gameHasNotStarted())

        game.playerChooses(BOTTOM_RIGHT)

        assertTrue("game should not change state of a previous snapshot", firstSnapshot.gameHasNotStarted())
        assertFalse(game.snapshot().gameHasNotStarted())
    }
}