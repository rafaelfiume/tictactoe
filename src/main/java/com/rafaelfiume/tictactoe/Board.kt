package com.rafaelfiume.tictactoe

interface Board {

    fun hasWinner(): Boolean

    fun markPositionIfAvailable(position: BoardPosition, player: Player): Boolean

    fun topLeft(): Char
    fun topCenter(): Char
    fun topRight(): Char
    fun midLeft(): Char
    fun center(): Char
    fun midRight(): Char
    fun bottomLeft(): Char
    fun bottomCenter(): Char
    fun bottomRight(): Char

    fun snapshot(): Board
}