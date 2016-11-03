package com.rafaelfiume.tictactoe

class TurnOfTwo private constructor(
        private var currentPlayer: Player,
        private var currentTurn: Int) : Turn {

    internal constructor() : this(Player.X, 1)

    override fun snapshot() = TurnOfTwo(this.currentPlayer, this.currentTurn)

    override fun switchTurnIf(gameIsOn: Boolean) {
        if (gameIsOn) {
            this.currentTurn++
            this.currentPlayer = if (currentPlayer == Player.X) Player.O else Player.X
        }
    }

    override fun hasNoMoreTurns() = currentTurn == 10

    override fun currentPlayer() = currentPlayer
}
