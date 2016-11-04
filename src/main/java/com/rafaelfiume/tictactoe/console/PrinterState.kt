package com.rafaelfiume.tictactoe.console

interface PrinterState {

    fun gameDescription(): String
    fun board(): String
    fun gameStatus(): String
}