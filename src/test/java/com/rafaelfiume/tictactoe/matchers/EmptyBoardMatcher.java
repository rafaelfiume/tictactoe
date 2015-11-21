package com.rafaelfiume.tictactoe.matchers;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

public class EmptyBoardMatcher extends TypeSafeMatcher<String> {

    // Duplicated? Yes! That ensures we are not making a mistake asserting a wrong board
    private final String expectedBoard = "" +
            "   |   |   \n" +
            "---+---+---\n" +
            "   |   |   \n" +
            "---+---+---\n" +
            "   |   |   \n";

    @Override
    protected boolean matchesSafely(String resultBoard) {
        return expectedBoard.equals(resultBoard);
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("a board:\n" + expectedBoard);
    }

    @Override
    protected void describeMismatchSafely(String actual, Description mismatchDescription) {
        mismatchDescription.appendText("game board was:\n" + actual);
    }
}
