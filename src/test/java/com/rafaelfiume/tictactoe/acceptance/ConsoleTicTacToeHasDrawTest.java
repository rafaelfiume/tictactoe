package com.rafaelfiume.tictactoe.acceptance;

import com.googlecode.yatspec.junit.SpecRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.rafaelfiume.tictactoe.matchers.BoardMatcher.showsABoardLike;
import static java.lang.System.lineSeparator;
import static org.hamcrest.Matchers.is;

@RunWith(SpecRunner.class)
public class ConsoleTicTacToeHasDrawTest extends AbstractConsoleTicTacToeTest {

    @Test
    public void gameEndsInADrawWhenPlayersCantGetThreeInARowBeforeTurnsEnd() throws Exception {
        given(player_X_marksTopLeftPosition());
        and(player_O_marksCenterPosition());
        and(player_X_marksMidLeftPosition());
        and(player_O_marksDownLeftPosition());
        and(player_X_marksTopRightPosition());
        and(player_O_marksDownRightPosition());
        and(player_X_marksDownCenterPosition());
        and(player_O_marksTopCenterPosition());

        when(player_X_marksMidRightPosition());
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
