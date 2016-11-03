package com.rafaelfiume.tictactoe.support

import com.rafaelfiume.tictactoe.Board
import com.rafaelfiume.tictactoe.BoardPosition
import com.rafaelfiume.tictactoe.BoardPosition.*
import com.rafaelfiume.tictactoe.Turn
import com.rafaelfiume.tictactoe.TurnOfTwo

@Suppress("unused") // Used via companion method aBoard()
class BoardBuilder private constructor(turn: Turn) {

    private val board = Board(turn)

    fun withPlayerXChoosing(boardPosition: BoardPosition): BoardBuilder {
        board.playerChooses(boardPosition)
        return this
    }

    fun withPlayerOChoosing(boardPosition: BoardPosition): BoardBuilder {
        board.playerChooses(boardPosition)
        return this
    }

    fun withPlayerXChoosingAnUnknownCell(): BoardBuilder {
        board.playerChooses(UNKNOWN)
        return this
    }

    fun build(): Board = board

    companion object {

        fun aBoard(turn: Turn = TurnOfTwo()) = BoardBuilder(turn)

        fun emptyBoard(turn: Turn = TurnOfTwo()) = aBoard(turn).build()

        fun aBoardWithPlayerXWinningWithVerticalLineOnTheLeft(): Board {
            return aBoard()
                    .withPlayerXChoosing(TOP_LEFT)
                    .withPlayerOChoosing(CENTER)
                    .withPlayerXChoosing(MID_LEFT)
                    .withPlayerOChoosing(BoardPosition.MID_RIGHT)
                    .withPlayerXChoosing(BoardPosition.BOTTOM_LEFT)
                    .build()
        }

        fun aGameWithPlayer_O_WinningWithAnHorizontalLineOnTheBottom(): Board {
            return aBoard()
                    .withPlayerXChoosing(TOP_RIGHT)
                    .withPlayerOChoosing(BOTTOM_LEFT)
                    .withPlayerXChoosing(CENTER)
                    .withPlayerOChoosing(BOTTOM_CENTER)
                    .withPlayerXChoosing(MID_RIGHT)
                    .withPlayerOChoosing(BOTTOM_RIGHT).build()
        }

        fun aGameEndingWithNoWinner(): Board {
            return aBoard()
                    .withPlayerXChoosing(TOP_LEFT)
                    .withPlayerOChoosing(CENTER)
                    .withPlayerXChoosing(MID_LEFT)
                    .withPlayerOChoosing(BOTTOM_LEFT)
                    .withPlayerXChoosing(TOP_RIGHT)
                    .withPlayerOChoosing(BOTTOM_RIGHT)
                    .withPlayerXChoosing(BOTTOM_CENTER)
                    .withPlayerOChoosing(TOP_CENTER)
                    .withPlayerXChoosing(MID_RIGHT).build()
        }
    }

}
