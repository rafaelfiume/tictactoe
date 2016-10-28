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
    public void tellPlayerToChooseAnEmptyCellInTheBoardWhenShePicksUpOneThatHasBeenAlreadyChosen() throws Exception {
        given(player_X_marksTopLeftPosition());

        when(player_O_marksTopLeftPosition());
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
        given(player_X_marksTopLeftPosition());

        when(player_O_entersInvalidCellInTheBoard());
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
