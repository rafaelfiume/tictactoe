package com.rafaelfiume.tictactoe.console

import com.rafaelfiume.tictactoe.Board
import com.rafaelfiume.tictactoe.Game
import com.rafaelfiume.tictactoe.GameRenderer
import com.rafaelfiume.tictactoe.TurnOfTwo

class ConsoleGameRunner(
        private val game: Game,
        private val input: ConsoleInputReader,
        private val renderer: GameRenderer) {

    fun start() {
        renderer.show(game.snapshot())

        while (game.gameIsOn()) {
            game.playerChooses(input.readUserInput())
            renderer.show(game.snapshot())
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

            ConsoleGameRunner(Board(TurnOfTwo()), input, ConsoleGameRenderer()).start() //Board(TurnOfTwo()) is a little weird
        }

        private fun isBotModeIn(args: Array<String>): Boolean {
            return args.size >= 1 && BOT_MODE == args[0]
        }
    }


}
