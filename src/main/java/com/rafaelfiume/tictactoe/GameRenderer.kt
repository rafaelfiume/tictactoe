package com.rafaelfiume.tictactoe

interface GameRenderer {

    fun render(gameSnapshot: Board): String
}