package com.rafaelfiume.tictactoe;

import com.rafaelfiume.tictactoe.support.BoardBuilder;
import org.junit.Test;

import static com.rafaelfiume.tictactoe.matchers.BoardMatcher.showsABoardLike;
import static com.rafaelfiume.tictactoe.matchers.EmptyBoardMatcher.anEmptyBoard;
import static com.rafaelfiume.tictactoe.support.BoardBuilder.aBoardWithPlayerXWinningWithVerticalLineOnTheLeft;
import static com.rafaelfiume.tictactoe.support.BoardBuilder.aBoardwithPlayerOWinningWithAnHorizontalLineOnTheBottom;
import static java.lang.System.lineSeparator;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ConsoleTicTacToePrinterTest {

    @Test
    public void printEmptyBoardWhenThereAreNoMovesYet() {
        final Board emptyBoard = new BoardBuilder().build().currentGameSnapshot();
        final ConsoleTicTacToePrinter print = new ConsoleTicTacToePrinter(emptyBoard);

        assertThat(print.gameDescription(), is("Game Board Creation..."));
        assertThat(print.board(), is(anEmptyBoard()));
        assertThat(print.gameStatus(), is("Board Created." + lineSeparator() + "The game will start with Player X" + lineSeparator() + "Choose Position:"));
    }

    @Test
    public void printPlayerXWinsWhenHeGetsThreeInAVerticalRow() {
        final Board playerXWon = aBoardWithPlayerXWinningWithVerticalLineOnTheLeft().currentGameSnapshot();
        final ConsoleTicTacToePrinter print = new ConsoleTicTacToePrinter(playerXWon);

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
        final Board playerOWon = aBoardwithPlayerOWinningWithAnHorizontalLineOnTheBottom().currentGameSnapshot();
        final ConsoleTicTacToePrinter print = new ConsoleTicTacToePrinter(playerOWon);

        assertThat(print.gameDescription(), is("Player O:"));
        assertThat(print.board(), showsABoardLike(
                        "   |   | X " + lineSeparator() +
                        "---+---+---" + lineSeparator() +
                        "   | X | X " + lineSeparator() +
                        "---+---+---" + lineSeparator() +
                        " O | O | O "));
        assertThat(print.gameStatus(), is("PLAYER O WON!"));
    }
}
