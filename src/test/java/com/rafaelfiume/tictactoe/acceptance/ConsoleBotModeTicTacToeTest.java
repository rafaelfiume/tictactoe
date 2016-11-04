package com.rafaelfiume.tictactoe.acceptance;

import com.googlecode.yatspec.junit.SpecResultListener;
import com.googlecode.yatspec.junit.SpecRunner;
import com.googlecode.yatspec.junit.WithCustomResultListeners;
import com.googlecode.yatspec.plugin.sequencediagram.SequenceDiagramGenerator;
import com.googlecode.yatspec.plugin.sequencediagram.SvgWrapper;
import com.googlecode.yatspec.rendering.html.DontHighlightRenderer;
import com.googlecode.yatspec.rendering.html.HtmlResultRenderer;
import com.googlecode.yatspec.rendering.html.index.HtmlIndexRenderer;
import com.googlecode.yatspec.state.givenwhenthen.GivensBuilder;
import com.googlecode.yatspec.state.givenwhenthen.StateExtractor;
import com.googlecode.yatspec.state.givenwhenthen.TestState;
import com.rafaelfiume.tictactoe.TttGame;
import com.rafaelfiume.tictactoe.console.BotConsoleInputReader;
import com.rafaelfiume.tictactoe.console.ConsoleGameRunner;
import com.rafaelfiume.tictactoe.support.RecordConsoleOutputRenderer;
import org.hamcrest.Matcher;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.googlecode.totallylazy.Sequences.sequence;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.is;

@RunWith(SpecRunner.class)
public class ConsoleBotModeTicTacToeTest extends TestState implements WithCustomResultListeners {

    private static final int TURN_DURATION_IN_SECONDS = 0;

    private final RecordConsoleOutputRenderer consoleRenderer = new RecordConsoleOutputRenderer();

    private final ConsoleGameRunner gameRunner = new ConsoleGameRunner(
            TttGame.Factory.newGame(),
            new BotConsoleInputReader(TURN_DURATION_IN_SECONDS),
            consoleRenderer
    );

    @Test
    public void computerDoesAllThePlayingWhenTicTacToeIsInBotMode() throws Exception {
        given(appIsUpAndRunningInBotMode());

        then(theGameStatus(), anyOf(status("PLAYER X WON!"), status("PLAYER O WON!"), orStatus("GAME ENDS WITH A DRAW!")));
    }

    @Override
    public Iterable<SpecResultListener> getResultListeners() throws Exception {
        return sequence(
                new HtmlResultRenderer().
                        withCustomHeaderContent(SequenceDiagramGenerator.getHeaderContentForModalWindows()).
                        withCustomRenderer(SvgWrapper.class, new DontHighlightRenderer<>()),
                new HtmlIndexRenderer()).
                safeCast(SpecResultListener.class);
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
