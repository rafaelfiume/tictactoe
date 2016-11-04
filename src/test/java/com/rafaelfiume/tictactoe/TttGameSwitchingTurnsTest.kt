package com.rafaelfiume.tictactoe

import com.rafaelfiume.tictactoe.support.TestingBoards.givenABoard
import com.rafaelfiume.tictactoe.support.TestingTurns.givenATurn
import org.junit.Test
import org.mockito.BDDMockito.*
import org.mockito.Matchers.anyBoolean

class TttGameSwitchingTurnsTest {

    @Test
    fun changesTurnWhenPlayerMarksACellInTheBoard() {
        val turn = givenATurn()
        val board = givenABoard()
        val game = aGameWith(board, turn)
        val validPosition = true
        given(
            board.markPositionIfAvailable(anyBoardPosition(), firstPlayer())
        ).willReturn(validPosition)

        game.playerChooses(anyBoardPosition())

        then(turn).should(times(1)).switchTurnIf(gameIsOn = true)
    }

    @Test
    fun doesNotChangeTurnWhenPlayerTriesToMarkAnAlreadyOccupiedCellInTheBoard() {
        val turn = givenATurn()
        val board = givenABoard()
        val game = aGameWith(board, turn)
        val validPosition = false
        given(
            board.markPositionIfAvailable(anyBoardPosition(), firstPlayer())
        ).willReturn(validPosition)

        game.playerChooses(anyBoardPosition())

        then(turn).should(never()).switchTurnIf(gameIsOn = anyBoolean())
    }

    private fun aGameWith(board: Board, turn: Turn) = TttGame(board, turn)

    private fun anyBoardPosition() = BoardPosition.CENTER

    private fun firstPlayer() = Player.X
}