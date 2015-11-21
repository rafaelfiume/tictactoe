package com.rafaelfiume.tictactoe;

import com.rafaelfiume.tictactoe.support.BoardBuilder;
import org.junit.Before;
import org.junit.Test;

import static com.rafaelfiume.tictactoe.BoardPosition.*;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class BoardTest {

    @Test
    public void gameIsNotOverIfThereIsNoWinnerOrDraw() {
        final Board board = new BoardBuilder().withPlayerXChoosing(CENTER).build();

        assertFalse(board.isGameOver());
    }

    @Test
    public void playerWinsWithVerticalLineInTheMiddle() {
        final Board board = new BoardBuilder()
                .withPlayerXChoosing(CENTER)
                .withPlayerOChoosing(MID_RIGHT)
                .withPlayerXChoosing(TOP_CENTER)
                .withPlayerOChoosing(TOP_RIGHT)
                .withPlayerXChoosing(DOWN_CENTER)
                .build();

        assertTrue("game should be over", board.isGameOver());
        assertThat(board.winner(), is(Player.X));
    }

    @Test
    public void playerWinsWithVerticalLineInTheRight() {
        final Board board = new BoardBuilder()
                .withPlayerXChoosing(TOP_RIGHT)
                .withPlayerOChoosing(CENTER)
                .withPlayerXChoosing(MID_RIGHT)
                .withPlayerOChoosing(DOWN_CENTER)
                .withPlayerXChoosing(DOWN_RIGHT)
                .build();

        assertTrue("game should be over", board.isGameOver());
        assertThat(board.winner(), is(Player.X));
    }

    @Test
    public void playerWinsWithHorizontalLineInTheBottom() {
        final Board board = new BoardBuilder()
                .withPlayerXChoosing(TOP_RIGHT)
                .withPlayerOChoosing(DOWN_LEFT)
                .withPlayerXChoosing(CENTER)
                .withPlayerOChoosing(DOWN_CENTER)
                .withPlayerXChoosing(MID_RIGHT)
                .withPlayerOChoosing(DOWN_RIGHT)
                .build();

        assertTrue("game should be over", board.isGameOver());
        assertThat(board.winner(), is(Player.O));
    }

    @Test
    public void playerWinsWithDiagonalLine() {
        final Board board = new BoardBuilder()
                .withPlayerXChoosing(TOP_RIGHT)
                .withPlayerOChoosing(DOWN_LEFT)
                .withPlayerXChoosing(CENTER)
                .withPlayerOChoosing(DOWN_CENTER)
                .withPlayerXChoosing(DOWN_LEFT)
                .build();

        assertTrue("game should be over", board.isGameOver());
        assertThat(board.winner(), is(Player.X));
    }

    @Test
    public void boardReturnsASnaphostOfTheCurrentGame() {
        final Board snapshot = new BoardBuilder()
                .withPlayerXChoosing(TOP_RIGHT)
                .withPlayerOChoosing(CENTER)
                .withPlayerXChoosing(MID_RIGHT)
                .withPlayerOChoosing(DOWN_CENTER)
                .withPlayerXChoosing(DOWN_RIGHT)
                .build().currentGameSnapshot();

        assertTrue("snapshot should say that game is over", snapshot.isGameOver());
        assertThat(snapshot.winner(), is(Player.X));
        assertTrue(snapshot.isGameStarted());
    }

    @Test
    public void changingTheBoardDoesNotReflectsOnPreviousSnapshots() {
        final Board board = new BoardBuilder().build();

        final Board snapshot = board.currentGameSnapshot();
        assertFalse("snapshot should not say that game is over", snapshot.isGameOver());

        board.playerChooses(DOWN_RIGHT);
        assertFalse("board should not change state of a created snapshot", snapshot.isGameStarted());
    }

}
