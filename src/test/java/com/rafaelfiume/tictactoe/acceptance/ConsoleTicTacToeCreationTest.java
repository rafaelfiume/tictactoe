package com.rafaelfiume.tictactoe.acceptance;

import com.googlecode.yatspec.junit.SpecRunner;
import com.rafaelfiume.tictactoe.matchers.BoardMatcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.rafaelfiume.tictactoe.matchers.EmptyBoardMatcher.anEmptyBoard;
import static java.lang.System.lineSeparator;
import static org.hamcrest.Matchers.is;

@RunWith(SpecRunner.class)
public class ConsoleTicTacToeCreationTest extends AbstractConsoleTicTacToeTest {

    @Test
    public void appDisplaysBoardToUsersWhenItStarts() throws Exception {
        given(appIsUpAndRunning());

        then(theGameDescription(), is("Game Board Creation..."));
        then(theGame(), shows(anEmptyBoard()));
        then(theGameStatus(), is("The game will start with Player X" + lineSeparator() + "Choose position:"));
    }

    private TypeSafeMatcher<String> shows(BoardMatcher boardMatcher) {
        return boardMatcher;
    }

}
