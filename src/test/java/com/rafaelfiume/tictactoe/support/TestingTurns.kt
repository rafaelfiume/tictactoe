package com.rafaelfiume.tictactoe.support

import com.rafaelfiume.tictactoe.Player
import com.rafaelfiume.tictactoe.Turn
import com.rafaelfiume.tictactoe.TurnOfTwo
import org.mockito.BDDMockito.given
import org.mockito.BDDMockito.mock

object TestingTurns {

    fun givenATurn(): Turn {
        val turn = mock(Turn::class.java)
        given(turn.currentPlayer()).willReturn(Player.X)
        return turn
    }

    fun afterMore(numberOfTurns: Int, turn: TurnOfTwo) {
        for (number in 1..numberOfTurns) {
            turn.switchTurnIf(gameIsOn = true)
        }
    }

}