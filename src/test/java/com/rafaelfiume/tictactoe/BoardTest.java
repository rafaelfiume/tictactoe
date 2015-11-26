package com.rafaelfiume.tictactoe;

import com.rafaelfiume.tictactoe.support.BoardBuilder;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Test;

import static com.rafaelfiume.tictactoe.BoardPosition.*;
import static com.rafaelfiume.tictactoe.BoardTest.DrawMatcher.hasDraw;
import static com.rafaelfiume.tictactoe.BoardTest.PlayerWonMatcher.hasWinner;
import static com.rafaelfiume.tictactoe.support.BoardBuilder.aBoardWithAGameEndingWithADraw;
import static com.rafaelfiume.tictactoe.support.BoardBuilder.aBoardWithPlayerOWinningWithAnHorizontalLineOnTheBottom;
import static com.rafaelfiume.tictactoe.support.BoardBuilder.emptyBoard;
import static java.lang.String.format;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class BoardTest {

    @Test
    public void gameIsNotOverIfThereIsNoWinnerOrDraw() {
        final Board board = new BoardBuilder().withPlayerXChoosing(CENTER).build();

        assertFalse(board.isGameOver());
    }

    // Winning

    @Test
    public void playerWinsWithVerticalLineInTheMiddle() {
        final Board board = new BoardBuilder()
                .withPlayerXChoosing(CENTER)
                .withPlayerOChoosing(MID_RIGHT)
                .withPlayerXChoosing(TOP_CENTER)
                .withPlayerOChoosing(TOP_RIGHT)
                .withPlayerXChoosing(BOTTOM_CENTER)
                .build();

        assertThat(board, hasWinner(Player.X));
    }

    @Test
    public void playerWinsWithVerticalLineInTheRight() {
        final Board board = new BoardBuilder()
                .withPlayerXChoosing(TOP_RIGHT)
                .withPlayerOChoosing(CENTER)
                .withPlayerXChoosing(MID_RIGHT)
                .withPlayerOChoosing(BOTTOM_CENTER)
                .withPlayerXChoosing(BOTTOM_RIGHT)
                .build();

        assertThat(board, hasWinner(Player.X));

    }

    @Test
    public void playerWinsWithHorizontalLineInTheBottom() {
        assertThat(aBoardWithPlayerOWinningWithAnHorizontalLineOnTheBottom(), hasWinner(Player.O));
    }

    @Test
    public void playerWinsWithDiagonalLine() {
        final Board board = new BoardBuilder()
                .withPlayerXChoosing(TOP_RIGHT)
                .withPlayerOChoosing(TOP_CENTER)
                .withPlayerXChoosing(CENTER)
                .withPlayerOChoosing(BOTTOM_CENTER)
                .withPlayerXChoosing(BOTTOM_LEFT)
                .build();

        assertThat(board, hasWinner(Player.X));
    }

    // Draw

    @Test
    public void gameEndsInADrawWhenPlayersCantGetThreeInARow() {
        assertThat(aBoardWithAGameEndingWithADraw(), hasDraw());
    }

    // Validation

    @Test
    public void doNotChangeTurnWhenPlayerTriesToMarkAnAlreadyOccupiedCellInTheBoard() {// again, this regards turns not board (needs refactor to improve the design)
        // given
        Board board = emptyBoard();
        assertThat(board.currentTurn(), is(1));
        assertThat(board.currentPlayer(), is(Player.X));

        board.playerChooses(CENTER);
        assertThat(board.currentTurn(), is(2));
        assertThat(board.currentPlayer(), is(Player.O));

        // when
        board.playerChooses(CENTER);

        // then
        assertThat(board.currentPlayer(), is(Player.O));
        assertThat(board.currentTurn(), is(2));
    }

    @Test
    public void doNotChangeTurnWhenPlayerChoosesUnknownCellInTheBoard() {
        // given
        Board board = emptyBoard();
        assertThat(board.currentTurn(), is(1));
        assertThat(board.currentPlayer(), is(Player.X));

        // when
        board.playerChooses(UNKNOWN);

        // then
        assertThat(board.currentTurn(), is(1));
        assertThat(board.currentPlayer(), is(Player.X));
    }

    //
    //// MATCHER
    //

    static class PlayerWonMatcher extends GameResultMatcher {
        static Matcher<? super Board> hasWinner(Player winner) {
            return new PlayerWonMatcher(winner);
        }

        private PlayerWonMatcher(Player expectedWinner) {
            super(true, false, expectedWinner);
        }
    }

    static class DrawMatcher extends GameResultMatcher {
        static Matcher<? super Board> hasDraw() {
            return new DrawMatcher();
        }

        private DrawMatcher() {
            super(false, true, null);
        }
    }

    private static abstract class GameResultMatcher extends TypeSafeMatcher<Board> {

        private final Player expectedWinner;

        private final boolean expectsGameEndingWithAWinner;
        private final boolean expectsGameEndingWithADraw;

        private GameResultMatcher(boolean expectsGameEndingWithAWinner, boolean expectsGameEndingWithADraw, Player expectedWinner) {
            this.expectedWinner = expectedWinner;
            this.expectsGameEndingWithAWinner = expectsGameEndingWithAWinner;
            this.expectsGameEndingWithADraw = expectsGameEndingWithADraw;
        }

        @Override
        protected boolean matchesSafely(Board resultBoard) {
            return resultBoard.isGameOver()
                    && resultBoard.gameIsOverWithAWinner() == expectsGameEndingWithAWinner
                    && resultBoard.gameIsOverWithADraw() == expectsGameEndingWithADraw
                    && resultBoard.winner() == expectedWinner;
        }

        @Override
        public void describeTo(Description description) {
            description.appendText(boardDescription(true, expectsGameEndingWithAWinner, expectsGameEndingWithADraw, expectedWinner));
        }

        @Override
        protected void describeMismatchSafely(Board actual, Description mismatchDescription) {
            mismatchDescription.appendText(
                    boardDescription(actual.isGameOver(), actual.gameIsOverWithAWinner(), actual.gameIsOverWithADraw(), actual.winner()));
        }

        private String boardDescription(boolean gameOver, boolean withWinner, boolean withDraw, Player winner) {
            final String desc = "game is over: \"%s\"; with a winner: \"%s\"; with a draw: \"%s\"; and winner being: \"%s\"";
            return format(desc, gameOver, withWinner, withDraw, winner);
        }
    }

}
