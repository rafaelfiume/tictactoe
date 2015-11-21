package com.rafaelfiume.tictactoe;

import com.rafaelfiume.tictactoe.matchers.EmptyBoardMatcher;
import com.rafaelfiume.tictactoe.support.BoardBuilder;
import org.hamcrest.Matcher;
import org.junit.Test;

import static com.rafaelfiume.tictactoe.matchers.CustomBoardMatcher.aBoardLike;
import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertThat;

public class ConsoleGameModelTest {

    @Test
    public void printEmptyBoardWhenThereAreNoMovesYet() {
        final Board emptyBoard = new BoardBuilder().build();
        final ConsoleGameModel emptyGame = new ConsoleGameModel(emptyBoard);

        assertThat(emptyGame.gameDescription(), containsString("Game Board Creation..."));
        assertThat(emptyGame.board(), containsEmptyBoard());
        assertThat(emptyGame.gameStatus(), containsString("Board Created.\nThe game will start with Player X"));
    }

    @Test
    public void printPlayerXWinsWhenHeStrikesVerticalVictory() {
        final ConsoleGameModel emptyGame = player_X_withVerticalVictory();

        assertThat(emptyGame.gameDescription(), containsString("Player X:"));
        assertThat(emptyGame.board(), aBoardLike(
                        " X |   |   \n" +
                        "---+---+---\n" +
                        " X | O | O \n" +
                        "---+---+---\n" +
                        " X |   |   \n"));
        assertThat(emptyGame.gameStatus(), containsString("PLAYER X WON!"));
    }

    private ConsoleGameModel player_X_withVerticalVictory() {
        final Board board = new BoardBuilder()
                .withPlayerXChoosing(BoardPosition.TOP_LEFT)
                .withPlayerOChoosing(BoardPosition.CENTER)
                .withPlayerXChoosing(BoardPosition.MID_LEFT)
                .withPlayerOChoosing(BoardPosition.MID_RIGHT)
                .withPlayerXChoosing(BoardPosition.DOWN_LEFT)
                .build();

        return new ConsoleGameModel(board.currentGameSnapshot());
    }

    private Matcher<? super String> containsEmptyBoard() {
        return new EmptyBoardMatcher();
    }

}
