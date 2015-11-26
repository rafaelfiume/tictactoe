package com.rafaelfiume.tictactoe.matchers;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

import static java.lang.String.format;
import static java.lang.System.lineSeparator;

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
        description.appendText(format("a board:%s%s", lineSeparator(), expectedBoard));
    }

    @Override
    protected void describeMismatchSafely(String actual, Description mismatchDescription) {
        mismatchDescription.appendText(format("game board was:%s%s", lineSeparator(), actual));
    }
}
