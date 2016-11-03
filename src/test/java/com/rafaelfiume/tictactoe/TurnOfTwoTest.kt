package com.rafaelfiume.tictactoe

import org.hamcrest.Matchers.`is`
import org.junit.Assert.assertThat
import org.junit.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class TurnOfTwoTest {

    @Test
    fun returnsFirstCurrentPlayer() {
        assertThat(TurnOfTwo().currentPlayer(), `is`(Player.X))
    }

    @Test
    fun changesPlayerWhenSwitchingTurnAndGameIsStillOn() {
        // given
        val turns = TurnOfTwo()
        val gameIsOn = true

        // when
        turns.switchTurnIf(gameIsOn) // O
        turns.switchTurnIf(gameIsOn) // X

        // then
        assertThat(turns.currentPlayer(), `is`(Player.X))

        // when
        turns.switchTurnIf(gameIsOn) // O

        // then
        assertThat(turns.currentPlayer(), `is`(Player.O))
    }

    @Test
    fun doesNotchangePlayerWhenSwitchingTurnAndGameIsOver() {
        // given
        val turns = TurnOfTwo()

        // when
        turns.switchTurnIf(gameOver())

        // then
        assertThat(turns.currentPlayer(), `is`(Player.X))
    }

    @Test
    fun endsTurnAfter_10_Turns() {
        val turns = TurnOfTwo()
        assertFalse(turns.hasNoMoreTurns())

        afterMore(8, turns)
        assertFalse(turns.hasNoMoreTurns())

        afterMore(1, turns)
        assertTrue(turns.hasNoMoreTurns())
    }

    private fun afterMore(numberOfTurns: Int, turn: TurnOfTwo) {
        for (number in 1..numberOfTurns) {
            turn.switchTurnIf(true)
        }
    }

    private fun gameOver() = false

}