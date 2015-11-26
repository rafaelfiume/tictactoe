package com.rafaelfiume.tictactoe;

import com.rafaelfiume.tictactoe.support.BoardBuilder;
import org.junit.Test;

import static com.rafaelfiume.tictactoe.BoardPosition.*;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class BoardSnapshotTest {

    @Test
    public void boardReturnsASnapshotOfTheCurrentGame() {
        final Board snapshot = new BoardBuilder()
                .withPlayerXChoosing(TOP_RIGHT)
                .withPlayerOChoosing(CENTER)
                .withPlayerXChoosing(MID_RIGHT)
                .withPlayerOChoosing(BOTTOM_CENTER)
                .withPlayerXChoosing(BOTTOM_RIGHT)
                .build().currentGameSnapshot();

        assertTrue("snapshot should say that game is over", snapshot.isGameOver());
        assertThat(snapshot.winner(), is(Player.X));
        assertFalse(snapshot.gameHasNotStarted());
    }

    @Test
    public void changingTheBoardDoesNotReflectsOnPreviousSnapshots() {
        final Board board = new BoardBuilder().build();

        final Board snapshot = board.currentGameSnapshot();
        assertFalse("snapshot should not say that game is over", snapshot.isGameOver());

        board.playerChooses(BOTTOM_RIGHT);
        assertTrue("board should not change state of a created snapshot", snapshot.gameHasNotStarted());
    }

}
