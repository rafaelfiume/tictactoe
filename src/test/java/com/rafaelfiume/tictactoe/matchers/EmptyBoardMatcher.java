package com.rafaelfiume.tictactoe.matchers;

import static java.lang.System.lineSeparator;

public class EmptyBoardMatcher extends BoardMatcher {

    // Duplicated? Yes! That ensures we are not making a mistake asserting a wrong board
    private static final String EXPECTED_BOARD = "" +
            "   |   |   " + lineSeparator() +
            "---+---+---" + lineSeparator() +
            "   |   |   " + lineSeparator() +
            "---+---+---" + lineSeparator() +
            "   |   |   ";


    public EmptyBoardMatcher() {
        super(EXPECTED_BOARD);
    }

}
