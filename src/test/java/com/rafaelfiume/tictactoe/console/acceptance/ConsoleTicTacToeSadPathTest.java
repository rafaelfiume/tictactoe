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
public class ConsoleTicTacToeSadPathTest extends AbstractConsoleTicTacToeTest {

    @Test
    public void tellPlayerToChooseAnEmptyCellInTheBoardWhenShePicksUpOneThatHasBeenAlreadyChosen() throws Exception {
        when(player_X_marksTopLeftPosition());
        and(player_O_marksTopLeftPosition());
        andAppIsUpAndRunning();

        then(theGameDescription(), is("Player O:"));
        then(theGame(), showsABoardLike(
                        " X |   |   " + lineSeparator() +
                        "---+---+---" + lineSeparator() +
                        "   |   |   " + lineSeparator() +
                        "---+---+---" + lineSeparator() +
                        "   |   |   "));
        then(theGameStatus(), is("Choose Position:"));
    }

    @Test
    public void tellPlayerToChooseAValidCellInTheBoardWhenHeEntersAnUnknownPosition() throws Exception {
        when(player_X_marksTopLeftPosition());
        and(player_O_entersInvalidCellInTheBoard());
        andAppIsUpAndRunning();

        then(theGameDescription(), is("Player O:"));
        then(theGame(), showsABoardLike(
                        " X |   |   " + lineSeparator() +
                        "---+---+---" + lineSeparator() +
                        "   |   |   " + lineSeparator() +
                        "---+---+---" + lineSeparator() +
                        "   |   |   "));
        then(theGameStatus(), is("Choose Position:"));
    }

}
