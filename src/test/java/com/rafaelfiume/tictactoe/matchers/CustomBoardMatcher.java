package com.rafaelfiume.tictactoe.matchers;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

public class CustomBoardMatcher extends TypeSafeMatcher<String> {

    private final String expectedBoard;

    public static CustomBoardMatcher aBoardLike(String board) {
        return new CustomBoardMatcher(board);
    }

    public CustomBoardMatcher(String expectedBoard) {
        this.expectedBoard = expectedBoard;
    }

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
