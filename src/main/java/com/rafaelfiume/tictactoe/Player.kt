package com.rafaelfiume.tictactoe

enum class Player(private val symbol: Char) {

    X('X'), O('O');

    fun symbol(): Char = symbol

}
