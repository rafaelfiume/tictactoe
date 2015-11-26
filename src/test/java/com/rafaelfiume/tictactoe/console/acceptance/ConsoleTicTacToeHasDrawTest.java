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
public class ConsoleTicTacToeHasDrawTest extends AbstractConsoleTicTacToeTest {

    @Test
    public void gameEndsInADrawWhenPlayersCantGetThreeInARowBeforeTurnsEnd() throws Exception {
        when(player_X_marksTopLeftPosition());
        and(player_O_marksCenterPosition());
        and(player_X_marksMidLeftPosition());
        and(player_O_marksDownLeftPosition());
        and(player_X_marksTopRightPosition());
        and(player_O_marksDownRightPosition());
        and(player_X_marksDownCenterPosition());
        and(player_O_marksTopCenterPosition());
        and(player_X_marksMidRightPosition());

        andAppIsUpAndRunning();

        then(theGameDescription(), is("No More Turns Left :-)"));
        then(theGame(), showsABoardLike(
                        " X | O | X " + lineSeparator() +
                        "---+---+---" + lineSeparator() +
                        " X | O | X " + lineSeparator() +
                        "---+---+---" + lineSeparator() +
                        " O | X | O "));
        then(theGameStatus(), is("GAME ENDS WITH A DRAW!"));
    }

}
