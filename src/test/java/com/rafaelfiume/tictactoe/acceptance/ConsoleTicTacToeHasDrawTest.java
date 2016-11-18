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
    public void gameEndsInADrawWhenPlayersCantGetThreeInARow() throws Exception {
        given(player_X_types_1());
        and(player_O_types_5());
        and(player_X_types_4());
        and(player_O_types_7());
        and(player_X_types_3());
        and(player_O_types_9());
        and(player_X_types_8());
        and(player_O_types_2());

        when(player_X_types_6());
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
