package com.rafaelfiume.tictactoe

interface Turn {

    fun switchTurnIf(gameIsOn: Boolean)

    fun snapshot(): TurnOfTwo

    fun hasNoMoreTurns(): Boolean

    fun currentPlayer(): Player

}