package com.rafaelfiume.tictactoe.acceptance;

import com.googlecode.yatspec.junit.SpecRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.rafaelfiume.tictactoe.matchers.BoardMatcher.showsABoardLike;
import static java.lang.System.lineSeparator;
import static org.hamcrest.Matchers.is;

@RunWith(SpecRunner.class)
public class ConsoleTicTacToeHasWinnerTest extends AbstractConsoleTicTacToeTest {

    @Test
    public void playerXWinsWhenGettingThreeInAVerticalRow() throws Exception {
        given(player_X_types_1());
        and(player_O_types_5());
        and(player_X_types_4());
        and(player_O_types_6());

        when(player_X_types_7());
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
        given(player_X_types_1());
        and(player_O_types_5());
        and(player_X_types_2());
        and(player_O_types_4());
        and(player_X_types_7());

        when(player_O_types_6());
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
        given(player_X_types_1());
        and(player_O_types_4());
        and(player_X_types_5());
        and(player_O_types_6());

        when(player_X_types_9());
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
