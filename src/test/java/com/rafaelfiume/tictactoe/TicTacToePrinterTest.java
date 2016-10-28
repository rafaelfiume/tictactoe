package com.rafaelfiume.tictactoe;

import com.rafaelfiume.tictactoe.support.BoardBuilder;
import org.junit.Test;

import static com.rafaelfiume.tictactoe.BoardPosition.TOP_RIGHT;
import static com.rafaelfiume.tictactoe.matchers.BoardMatcher.showsABoardLike;
import static com.rafaelfiume.tictactoe.matchers.EmptyBoardMatcher.anEmptyBoard;
import static com.rafaelfiume.tictactoe.support.BoardBuilder.aBoardWithPlayerXWinningWithVerticalLineOnTheLeft;
import static com.rafaelfiume.tictactoe.support.BoardBuilder.aGameWithPlayer_O_WinningWithAnHorizontalLineOnTheBottom;
import static com.rafaelfiume.tictactoe.support.BoardBuilder.emptyBoard;
import static java.lang.System.lineSeparator;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class TicTacToePrinterTest {

    @Test
    public void printEmptyBoardWhenThereAreNoMovesYet() {
        final Game emptyGame = emptyBoard().snapshot();
        final TicTacToePrinter print = new TicTacToePrinter(emptyGame);

        assertThat(print.gameDescription(), is("Game Board Creation..."));
        assertThat(print.board(), is(anEmptyBoard()));
        assertThat(print.gameStatus(), is("Board Created." + lineSeparator() + "The game will start with Player X" + lineSeparator() + "Choose Position:"));
    }

    @Test
    public void printPlayerXWinsWhenHeGetsThreeInAVerticalRow() {
        final Game playerXWon = aBoardWithPlayerXWinningWithVerticalLineOnTheLeft().snapshot();
        final TicTacToePrinter print = new TicTacToePrinter(playerXWon);

        assertThat(print.gameDescription(), is("Player X:"));
        assertThat(print.board(), showsABoardLike(
                        " X |   |   " + lineSeparator() +
                        "---+---+---" + lineSeparator() +
                        " X | O | O " + lineSeparator() +
                        "---+---+---" + lineSeparator() +
                        " X |   |   "));
        assertThat(print.gameStatus(), is("PLAYER X WON!"));
    }

    @Test
    public void printPlayerOWinsWhenSheStrikesHorizontalVictory() {
        final Game playerOWon = aGameWithPlayer_O_WinningWithAnHorizontalLineOnTheBottom().snapshot();
        final TicTacToePrinter print = new TicTacToePrinter(playerOWon);

        assertThat(print.gameDescription(), is("Player O:"));
        assertThat(print.board(), showsABoardLike(
                        "   |   | X " + lineSeparator() +
                        "---+---+---" + lineSeparator() +
                        "   | X | X " + lineSeparator() +
                        "---+---+---" + lineSeparator() +
                        " O | O | O "));
        assertThat(print.gameStatus(), is("PLAYER O WON!"));
    }

    @Test
    public void printGameEndsWithDrawWhenThereAreNoMoreTurnsAndNoWinners() {
        final Game draw = BoardBuilder.aGameEndingWithNoWinner().snapshot();
        final TicTacToePrinter print = new TicTacToePrinter(draw);

        assertThat(print.gameDescription(), is("No More Turns Left :-)"));
        assertThat(print.board(), showsABoardLike(
                        " X | O | X " + lineSeparator() +
                        "---+---+---" + lineSeparator() +
                        " X | O | X " + lineSeparator() +
                        "---+---+---" + lineSeparator() +
                        " O | X | O "));
        assertThat(print.gameStatus(), is("GAME ENDS WITH A DRAW!"));
    }

    @Test
    public void printPlayerHasToChooseAnotherCellInTheBoardWhenSheTriesToMarkAnOccupiedSpaceInTheBoard() {
        final Game boardWithDisputedCell = new BoardBuilder()
                .withPlayerXChoosing(TOP_RIGHT)
                .withPlayerOChoosing(TOP_RIGHT)
                .build().snapshot();

        final TicTacToePrinter print = new TicTacToePrinter(boardWithDisputedCell);

        assertThat(print.gameDescription(), is("Player O:"));
        assertThat(print.board(), showsABoardLike(
                        "   |   | X " + lineSeparator() +
                        "---+---+---" + lineSeparator() +
                        "   |   |   " + lineSeparator() +
                        "---+---+---" + lineSeparator() +
                        "   |   |   "));
        assertThat(print.gameStatus(), is("Choose Position:"));
    }

    @Test
    public void printPlayerHasToChooseAnotherCellInTheBoardWhenHePicksUpAnUnknownOne() {
        final Game board = new BoardBuilder()
                .withPlayerXChoosingAnUnknownCell()
                .build().snapshot();

        final TicTacToePrinter print = new TicTacToePrinter(board);

        assertThat(print.gameDescription(), is("Player X:"));
        assertThat(print.board(), showsABoardLike(
                        "   |   |   " + lineSeparator() +
                        "---+---+---" + lineSeparator() +
                        "   |   |   " + lineSeparator() +
                        "---+---+---" + lineSeparator() +
                        "   |   |   "));
        assertThat(print.gameStatus(), is("Choose Position:"));
    }
}
