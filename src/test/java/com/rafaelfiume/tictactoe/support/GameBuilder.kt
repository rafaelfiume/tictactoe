package com.rafaelfiume.tictactoe.support

import com.rafaelfiume.tictactoe.*
import com.rafaelfiume.tictactoe.BoardPosition.*

@Suppress("unused") // used via companion method aBoard()
class GameBuilder private constructor(board: Board = TttBoard(), turn: Turn = TurnOfTwo()) {

    private val game = TttGame(board, turn)

    fun withPlayerXChoosing(boardPosition: BoardPosition): GameBuilder {
        game.playerChooses(boardPosition)
        return this
    }

    fun withPlayerOChoosing(boardPosition: BoardPosition): GameBuilder = withPlayerXChoosing(boardPosition)

    fun build(): Game = game

    companion object Factory {

        fun aGame(): GameBuilder = GameBuilder()

        fun aBoardWithPlayerXWinningWithVerticalLineOnTheLeft(): Game {
            val game = TttGame(TttBoard(), TurnOfTwo())

            game.playerChooses(TOP_LEFT)
            game.playerChooses(CENTER)
            game.playerChooses(MID_LEFT)
            game.playerChooses(BoardPosition.MID_RIGHT)
            game.playerChooses(BoardPosition.BOTTOM_LEFT)

            return game
        }

        fun aGameWithPlayer_O_WinningWithAnHorizontalLineOnTheBottom(): Game {
            val game = TttGame(TttBoard(), TurnOfTwo())

            game.playerChooses(TOP_RIGHT)
            game.playerChooses(BOTTOM_LEFT)
            game.playerChooses(CENTER)
            game.playerChooses(BOTTOM_CENTER)
            game.playerChooses(MID_RIGHT)
            game.playerChooses(BOTTOM_RIGHT)

            return game
        }

        fun aGameEndingWithNoWinner(): Game {
            val game = TttGame(TttBoard(), TurnOfTwo())

            game.playerChooses(TOP_LEFT)
            game.playerChooses(CENTER)
            game.playerChooses(MID_LEFT)
            game.playerChooses(BOTTOM_LEFT)
            game.playerChooses(TOP_RIGHT)
            game.playerChooses(BOTTOM_RIGHT)
            game.playerChooses(BOTTOM_CENTER)
            game.playerChooses(TOP_CENTER)
            game.playerChooses(MID_RIGHT)

            return game
        }
    }

}
