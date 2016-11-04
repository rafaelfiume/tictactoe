package com.rafaelfiume.tictactoe


interface Game {

    fun playerChooses(boardPosition: BoardPosition)

    fun gameIsOn(): Boolean

    fun gameHasNotStarted(): Boolean

    fun winner(): Player

    fun hasWinner(): Boolean

    fun hasDraw(): Boolean

    fun snapshot(): Game

    // Board
    fun board(): Board

    // Turn
    fun currentPlayer(): Player

}
