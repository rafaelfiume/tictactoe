package com.rafaelfiume.tictactoe.console.acceptance;

import com.googlecode.yatspec.junit.SpecRunner;
import com.googlecode.yatspec.state.givenwhenthen.ActionUnderTest;
import com.googlecode.yatspec.state.givenwhenthen.GivensBuilder;
import com.googlecode.yatspec.state.givenwhenthen.StateExtractor;
import com.googlecode.yatspec.state.givenwhenthen.TestState;
import com.rafaelfiume.tictactoe.Board;
import com.rafaelfiume.tictactoe.console.BotConsoleInputReader;
import com.rafaelfiume.tictactoe.console.ConsoleGameRunner;
import com.rafaelfiume.tictactoe.matchers.BoardMatcher;
import com.rafaelfiume.tictactoe.support.ConsoleInputReaderStub;
import com.rafaelfiume.tictactoe.support.RecordConsoleOutputRenderer;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.rafaelfiume.tictactoe.matchers.EmptyBoardMatcher.anEmptyBoard;
import static java.lang.System.lineSeparator;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.is;

@RunWith(SpecRunner.class)
public class ConsoleBotTicTacToeTest extends TestState {

    private static final int TURN_DURATION_IN_SECONDS = 0;

    private final RecordConsoleOutputRenderer consoleRenderer = new RecordConsoleOutputRenderer();
    private final ConsoleGameRunner gameRunner = new ConsoleGameRunner(
            new Board(),
            new BotConsoleInputReader(TURN_DURATION_IN_SECONDS),
            consoleRenderer
    );

    @Test
    public void appDisplaysBoardToUsersWhenItStarts() throws Exception {
        given(appIsUpAndRunningInBotMode());

        // Computer does all the playing

        then(theGameStatus(), anyOf(status("PLAYER X WON!"), status("PLAYER O WON!"), orStatus("GAME ENDS WITH A DRAW!")));
    }

    private GivensBuilder appIsUpAndRunningInBotMode() {
        return givens -> {
            startAppWithAHack();
            return givens;
        };
    }

    private void startAppWithAHack() {
        try {
            gameRunner.start();
        } catch (NullPointerException e) {
            // See AbstractConsoleTicTacToeTest
        }
    }

    private StateExtractor<String> theGameStatus() {
        return inputAndOutputs -> consoleRenderer.gameStatus();
    }

    private Matcher<Object> status(String s) {
        return is(s);
    }

    private Matcher<? super Object> orStatus(String s) {
        return status(s);
    }
}
