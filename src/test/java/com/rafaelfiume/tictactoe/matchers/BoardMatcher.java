package com.rafaelfiume.tictactoe.matchers;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

public class BoardMatcher extends TypeSafeMatcher<String> {

    private final String expectedBoard;

    public static BoardMatcher showsABoardLike(String board) {
        return new BoardMatcher(board);
    }

    public BoardMatcher(String expectedBoard) {
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
