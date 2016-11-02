package com.rafaelfiume.tictactoe


interface Game {

    ////////////////////////
    //        Turn        //
    ////////////////////////

    fun currentPlayer(): Player

    ////////////////////////
    //        Game        //
    ////////////////////////

    fun snapshot(): Game

    fun gameIsOn(): Boolean

    fun gameHasNotStarted(): Boolean

    fun hasWinner(): Boolean

    fun hasDraw(): Boolean

    fun winner(): Player

    ////////////////////////
    //        Board       //
    ////////////////////////

    fun playerChooses(boardPosition: BoardPosition)

    fun topLeft(): Char
    fun topCenter(): Char
    fun topRight(): Char
    fun midLeft(): Char
    fun center(): Char
    fun midRight(): Char
    fun bottomLeft(): Char
    fun bottomCenter(): Char
    fun bottomRight(): Char

}
