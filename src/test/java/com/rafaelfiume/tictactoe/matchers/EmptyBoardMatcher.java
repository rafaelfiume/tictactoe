package com.rafaelfiume.tictactoe.matchers;

public class EmptyBoardMatcher extends BoardMatcher {

    // Duplicated? Yes! That ensures we are not making a mistake asserting a wrong board
    private static final String EXPECTED_BOARD = "" +
            "   |   |   \n" +
            "---+---+---\n" +
            "   |   |   \n" +
            "---+---+---\n" +
            "   |   |   \n";


    public EmptyBoardMatcher() {
        super(EXPECTED_BOARD);
    }

}
