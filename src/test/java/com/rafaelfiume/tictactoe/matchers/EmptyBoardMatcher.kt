package com.rafaelfiume.tictactoe.matchers

import java.lang.System.lineSeparator

class EmptyBoardMatcher : BoardMatcher(EmptyBoardMatcher.EXPECTED_BOARD) {

    companion object {

        private val EXPECTED_BOARD = "" +
                "   |   |   " + lineSeparator() +
                "---+---+---" + lineSeparator() +
                "   |   |   " + lineSeparator() +
                "---+---+---" + lineSeparator() +
                "   |   |   "

        @JvmStatic fun anEmptyBoard() = EmptyBoardMatcher()
    }

}
