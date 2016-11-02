package com.rafaelfiume.tictactoe.support

import com.rafaelfiume.tictactoe.BoardPosition
import com.rafaelfiume.tictactoe.console.ConsoleInputReader
import java.util.*

import java.util.concurrent.ArrayBlockingQueue

class ConsoleInputReaderStub : ConsoleInputReader {

    private val positions: Queue<Int> = ArrayBlockingQueue(10)

    override fun readUserInput() = BoardPosition.of(positions.poll())

    fun willReturn(position: Int) {
        positions.add(position)
    }

}
