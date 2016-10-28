package com.rafaelfiume.tictactoe.console

import com.rafaelfiume.tictactoe.BoardPosition

interface ConsoleInputReader {

    fun readUserInput(): BoardPosition

}
