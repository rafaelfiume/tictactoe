package com.rafaelfiume.tictactoe

class TttGame private constructor(
        private val board: Board,
        private val turn: Turn,
        private var gameStarted: Boolean = false) : Game {

    internal constructor(board: Board, turn: Turn) : this(board, turn, false)

    override fun playerChooses(boardPosition: BoardPosition) {
        val validMove = board.markPositionIfAvailable(boardPosition, turn.currentPlayer())
        if (validMove) {
            turn.switchTurnIf(gameIsOn())
        }

        this.gameStarted = true
    }

    override fun gameHasNotStarted() = !gameStarted

    override fun gameIsOn() = !isGameOver()

    override fun hasWinner() = board.hasWinner()

    override fun hasDraw() = turn.hasNoMoreTurns() && !hasWinner()

    override fun winner() = turn.currentPlayer()

    override fun currentPlayer() = turn.currentPlayer()

    override fun snapshot() = TttGame(board.snapshot(), turn.snapshot(), gameStarted)

    override fun board() = board.snapshot()

    private fun isGameOver() = hasWinner() || hasDraw()

    companion object Factory {

        fun newGame(): Game = TttGame(TttBoard(), TurnOfTwo())

    }

}