package com.rafaelfiume.tictactoe.console

import com.rafaelfiume.tictactoe.BoardPosition

import java.util.ArrayList
import java.util.Collections
import java.util.Collections.shuffle

class BotConsoleInputReader(turnDurationInSeconds: Int) : ConsoleInputReader {

    private val turnDurationInSeconds: Int
    private val positionGenerator = RandomPositionGenerator()

    init {
        this.turnDurationInSeconds = turnDurationInSeconds * 1000
    }

    override fun readUserInput(): BoardPosition {
        waitTillTurnEnds()
        return BoardPosition.of(positionGenerator.next())
    }

    private fun waitTillTurnEnds() {
        try {
            Thread.sleep(turnDurationInSeconds.toLong())
        } catch (e: InterruptedException) {
            throw RuntimeException(e)
        }
    }

    private class RandomPositionGenerator internal constructor() {

        private val availablePositions = ArrayList<Int>()

        private var cursor = 0

        init {
            availablePositions += 1..MAX_POSITION
            shuffle(availablePositions)
        }

        /*
         * Relies on the client to not exceed the limit of #cursor invocation that is equal to MAX_POSITION
         */
        internal operator fun next(): Int {
            return availablePositions[index()]
        }

        private fun index(): Int {
            println(" ${availablePositions[cursor]}")
            return cursor++
        }

        companion object {

            private val MAX_POSITION = 9
        }

    }
}
