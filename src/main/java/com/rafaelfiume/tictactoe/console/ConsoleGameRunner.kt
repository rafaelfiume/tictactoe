package com.rafaelfiume.tictactoe.console

import com.rafaelfiume.tictactoe.Board
import com.rafaelfiume.tictactoe.GameRenderer

class ConsoleGameRunner(private val board: Board, private val input: ConsoleInputReader, private val renderer: GameRenderer) {

    fun start() {
        println(renderer.render(board.currentGameSnapshot())) 

        while (board.gameIsRunning()) {
            board.playerChooses(input.readUserInput())
            println(renderer.render(board.currentGameSnapshot()))
        }
    }

    companion object {

        private val BOT_MODE = "botmode"
        private val TURN_DURATION_WHEN_IN_BOT_MODE_IN_SECONDS = 2

        @JvmStatic fun main(args: Array<String>) {
            val input = if (isBotModeIn(args))
                BotConsoleInputReader(TURN_DURATION_WHEN_IN_BOT_MODE_IN_SECONDS)
            else
                BlockingConsoleInputReader()

            ConsoleGameRunner(Board(), input, ConsoleGameRenderer()).start()
        }

        private fun isBotModeIn(args: Array<String>): Boolean {
            return args.size >= 1 && BOT_MODE == args[0]
        }
    }


}
