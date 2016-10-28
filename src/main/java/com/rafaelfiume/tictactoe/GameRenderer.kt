package com.rafaelfiume.tictactoe

interface GameRenderer {

    fun show(gameSnapshot: Board): String
}