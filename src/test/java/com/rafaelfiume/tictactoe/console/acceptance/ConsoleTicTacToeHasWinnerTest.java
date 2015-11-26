package com.rafaelfiume.tictactoe.console.acceptance;

import com.googlecode.yatspec.junit.SpecRunner;
import com.googlecode.yatspec.state.givenwhenthen.ActionUnderTest;
import com.googlecode.yatspec.state.givenwhenthen.StateExtractor;
import com.googlecode.yatspec.state.givenwhenthen.TestState;
import com.rafaelfiume.tictactoe.Board;
import com.rafaelfiume.tictactoe.console.ConsoleGameRunner;
import com.rafaelfiume.tictactoe.support.ConsoleInputReaderStub;
import com.rafaelfiume.tictactoe.support.RecordConsoleOutputRenderer;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.rafaelfiume.tictactoe.matchers.BoardMatcher.showsABoardLike;
import static java.lang.System.lineSeparator;
import static org.hamcrest.Matchers.is;

@RunWith(SpecRunner.class)
public class ConsoleTicTacToeHasWinnerTest extends AbstractConsoleTicTacToeTest {

    @Test
    public void playerXWinsWhenGettingThreeInAVerticalRow() throws Exception {
        when(player_X_marksTopLeftPosition());
        and(player_O_marksCenterPosition());
        and(player_X_marksMidLeftPosition());
        and(player_O_marksMidRightPosition());
        and(player_X_marksDownLeftPosition());
        andAppIsUpAndRunning();

        then(theGameDescription(), is("Player X:"));
        then(theGame(), showsABoardLike(
                        " X |   |   " + lineSeparator() +
                        "---+---+---" + lineSeparator() +
                        " X | O | O " + lineSeparator() +
                        "---+---+---" + lineSeparator() +
                        " X |   |   "));
        then(theGameStatus(), is("PLAYER X WON!"));
    }

    @Test
    public void playerOWinsWhenGettingThreeInAHorizontalRow() throws Exception {
        when(player_X_marksTopLeftPosition());
        and(player_O_marksCenterPosition());
        and(player_X_marksTopCenterPosition());
        and(player_O_marksMidLeftPosition());
        and(player_X_marksDownLeftPosition());
        and(player_O_marksMidRightPosition());
        andAppIsUpAndRunning();

        then(theGameDescription(), is("Player O:"));
        then(theGame(), showsABoardLike(
                        " X | X |   " + lineSeparator() +
                        "---+---+---" + lineSeparator() +
                        " O | O | O " + lineSeparator() +
                        "---+---+---" + lineSeparator() +
                        " X |   |   "));
        then(theGameStatus(), is("PLAYER O WON!"));
    }

    @Test
    public void playerXWinsWhenGettingThreeInADiagonalRow() throws Exception {
        when(player_X_marksTopLeftPosition());
        and(player_O_marksMidLeftPosition());
        and(player_X_marksCenterPosition());
        and(player_O_marksMidRightPosition());
        and(player_X_marksDownRightPosition());
        andAppIsUpAndRunning();

        then(theGameDescription(), is("Player X:"));
        then(theGame(), showsABoardLike(
                        " X |   |   " + lineSeparator() +
                        "---+---+---" + lineSeparator() +
                        " O | X | O " + lineSeparator() +
                        "---+---+---" + lineSeparator() +
                        "   |   | X "));
        then(theGameStatus(), is("PLAYER X WON!"));
    }

}
