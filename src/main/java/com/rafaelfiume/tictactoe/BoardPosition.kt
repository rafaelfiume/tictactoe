package com.rafaelfiume.tictactoe

enum class BoardPosition constructor(private val input: Int, private val row: Int, private val column: Int) {

    TOP_LEFT(1, 0, 0)   , TOP_CENTER(2, 0, 1)   , TOP_RIGHT(3, 0, 2),
    MID_LEFT(4, 1, 0)   , CENTER(5, 1, 1)       , MID_RIGHT(6, 1, 2),
    BOTTOM_LEFT(7, 2, 0), BOTTOM_CENTER(8, 2, 1), BOTTOM_RIGHT(9, 2, 2),

    UNKNOWN(-1, -1, -1);

    fun row(): Int = row

    fun column(): Int = column

    companion object Factory {

        fun of(number: Int): BoardPosition {
            return BoardPosition.values().firstOrNull { it.input == number } ?: UNKNOWN
        }
    }

}
