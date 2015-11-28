package com.rafaelfiume.tictactoe.console.acceptance;

import com.googlecode.yatspec.junit.SpecResultListener;
import com.googlecode.yatspec.junit.WithCustomResultListeners;
import com.googlecode.yatspec.plugin.sequencediagram.SequenceDiagramGenerator;
import com.googlecode.yatspec.plugin.sequencediagram.SvgWrapper;
import com.googlecode.yatspec.rendering.html.DontHighlightRenderer;
import com.googlecode.yatspec.rendering.html.HtmlResultRenderer;
import com.googlecode.yatspec.rendering.html.index.HtmlIndexRenderer;
import com.googlecode.yatspec.state.givenwhenthen.ActionUnderTest;
import com.googlecode.yatspec.state.givenwhenthen.GivensBuilder;
import com.googlecode.yatspec.state.givenwhenthen.StateExtractor;
import com.googlecode.yatspec.state.givenwhenthen.TestState;
import com.rafaelfiume.tictactoe.Board;
import com.rafaelfiume.tictactoe.console.ConsoleGameRunner;
import com.rafaelfiume.tictactoe.support.ConsoleInputReaderStub;
import com.rafaelfiume.tictactoe.support.RecordConsoleOutputRenderer;

import static com.googlecode.totallylazy.Sequences.sequence;

public abstract class AbstractConsoleTicTacToeTest extends TestState implements WithCustomResultListeners {

    // TODO RF 26/11/15 Proposal to extract Turn from Board...
    //private final Board board = new Board();
    //private final Turn turn = mock(turn);
    //private final TicTacToe ticTacToe = new TicTacToe(board, turn);

    private final ConsoleInputReaderStub consoleInputReader = new ConsoleInputReaderStub();
    private final RecordConsoleOutputRenderer consoleRenderer = new RecordConsoleOutputRenderer();
    private final ConsoleGameRunner gameRunner = new ConsoleGameRunner(new Board(), consoleInputReader, consoleRenderer);

    @Override
    public Iterable<SpecResultListener> getResultListeners() throws Exception {
        return sequence(
                new HtmlResultRenderer().
                        withCustomHeaderContent(SequenceDiagramGenerator.getHeaderContentForModalWindows()).
                        withCustomRenderer(SvgWrapper.class, new DontHighlightRenderer()),
                new HtmlIndexRenderer()).
                safeCast(SpecResultListener.class);
    }

    protected ActionUnderTest player_X_marksTopLeftPosition() { return userHitsNumber(1); }
    protected ActionUnderTest player_O_marksTopLeftPosition() { return userHitsNumber(1); }
    protected ActionUnderTest player_X_marksTopCenterPosition() { return userHitsNumber(2); }
    protected ActionUnderTest player_O_marksTopCenterPosition() { return userHitsNumber(2); }
    protected ActionUnderTest player_X_marksTopRightPosition() { return userHitsNumber(3); }
    protected ActionUnderTest player_X_marksMidLeftPosition() { return userHitsNumber(4);}
    protected ActionUnderTest player_O_marksMidLeftPosition() { return userHitsNumber(4); }
    protected ActionUnderTest player_X_marksCenterPosition() { return userHitsNumber(5); }
    protected ActionUnderTest player_O_marksCenterPosition() { return userHitsNumber(5); }
    protected ActionUnderTest player_X_marksMidRightPosition() { return userHitsNumber(6); }
    protected ActionUnderTest player_O_marksMidRightPosition() { return userHitsNumber(6); }
    protected ActionUnderTest player_X_marksDownLeftPosition() { return userHitsNumber(7); }
    protected ActionUnderTest player_O_marksDownLeftPosition() { return userHitsNumber(7); }
    protected ActionUnderTest player_X_marksDownCenterPosition() { return userHitsNumber(8); }
    protected ActionUnderTest player_X_marksDownRightPosition() { return userHitsNumber(9); }
    protected ActionUnderTest player_O_marksDownRightPosition() { return userHitsNumber(9); }

    protected ActionUnderTest player_O_entersInvalidCellInTheBoard() {
        return userHitsNumber(99);
    }

    protected void andAppIsUpAndRunning() {
        startAppWithAHack();
    }

    protected GivensBuilder appIsUpAndRunning() {
        return givens -> {
            startAppWithAHack();
            return givens;
        };
    }

    protected TestState given(ActionUnderTest actionUnderTest) throws Exception {
        // Bypassing Yatspec framework since its original given implementation is not helping much in these tests (tests are reading way better now)
        return when(actionUnderTest);
    }

    protected TestState and(ActionUnderTest actionUnderTest) throws Exception {
        return when(actionUnderTest);
    }

    protected StateExtractor<Object> theGameDescription() {
        return inputAndOutputs -> consoleRenderer.gameDescription();
    }

    protected StateExtractor<String> theGame() {
        return inputAndOutputs -> consoleRenderer.boarGame();
    }

    protected StateExtractor<String> theGameStatus() {
        return inputAndOutputs -> consoleRenderer.gameStatus();
    }

    private ActionUnderTest userHitsNumber(int number) {
        return (givens, capturedInputAndOutputs) -> {
            consoleInputReader.willReturn(number);
            return capturedInputAndOutputs;
        };
    }

    private void startAppWithAHack() {
        try {
            gameRunner.start();
        } catch (NullPointerException e) {
            // Test and app work all right, but...
            // This is a massive hack to deal with premature termination: game is not over an ConsoleInputReader#readUserInput will blow up
            // TODO RF 25/11/2015 Improve the design to fix it
        }
    }

}
