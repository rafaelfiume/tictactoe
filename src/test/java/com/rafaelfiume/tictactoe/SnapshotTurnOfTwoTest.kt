package com.rafaelfiume.tictactoe

import com.rafaelfiume.tictactoe.support.TestingTurns.afterMore
import org.hamcrest.Matchers.`is`
import org.junit.Assert.assertThat
import org.junit.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class SnapshotTurnOfTwoTest {

    @Test
    fun changingTheTurnDoesNotReflectsOnPreviousSnapshots() {
        val turns = TurnOfTwo()
        val firstSnapshot = turns.snapshot()
        assertThat(firstSnapshot.currentPlayer(), `is`(Player.X))
        assertFalse(firstSnapshot.hasNoMoreTurns())

        // when
        turns.switchTurnIf(gameIsOn = true)

        // then
        assertThat("turns should not change state of a previous snapshot", firstSnapshot.currentPlayer(), `is`(Player.X))
        assertFalse(firstSnapshot.hasNoMoreTurns())
        assertThat(turns.snapshot().currentPlayer(), `is`(Player.O))

        // when
        afterMore(8, turns)

        // then
        assertFalse(firstSnapshot.hasNoMoreTurns())
        assertTrue(turns.snapshot().hasNoMoreTurns())
    }

}