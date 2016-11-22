package com.rafaelfiume.tictactoe.acceptance;

import com.googlecode.yatspec.junit.SpecRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.rafaelfiume.tictactoe.matchers.BoardMatcher.showsABoardLike;
import static java.lang.System.lineSeparator;
import static org.hamcrest.Matchers.is;

@RunWith(SpecRunner.class)
public class ConsoleTicTacToeSadPathTest extends AbstractConsoleTicTacToeTest {

    @Test
    public void playerHasToSelectAFreePositionInTheBoard() throws Exception {
        given(player_X_types_1());

        when(player_O_types_1());
        andAppIsUpAndRunning();

        then(theGameDescription(), is("Player O:"));
        then(theGame(), showsABoardLike(
                        " X |   |   " + lineSeparator() +
                        "---+---+---" + lineSeparator() +
                        "   |   |   " + lineSeparator() +
                        "---+---+---" + lineSeparator() +
                        "   |   |   "));
        then(theGameStatus(), is("Choose position:"));
    }

    @Test
    public void playerHasToSelectAValidPositionInTheBoard() throws Exception {
        given(player_X_types_1());

        when(player_O_typesInvalidStuff());
        andAppIsUpAndRunning();

        then(theGameDescription(), is("Player O:"));
        then(theGame(), showsABoardLike(
                        " X |   |   " + lineSeparator() +
                        "---+---+---" + lineSeparator() +
                        "   |   |   " + lineSeparator() +
                        "---+---+---" + lineSeparator() +
                        "   |   |   "));
        then(theGameStatus(), is("Choose position:"));
    }

}
