package com.rafaelfiume.tictactoe;

import com.googlecode.yatspec.junit.SpecRunner;
import com.googlecode.yatspec.state.givenwhenthen.ActionUnderTest;
import com.googlecode.yatspec.state.givenwhenthen.GivensBuilder;
import com.googlecode.yatspec.state.givenwhenthen.StateExtractor;
import com.googlecode.yatspec.state.givenwhenthen.TestState;
import com.rafaelfiume.tictactoe.matchers.EmptyBoardMatcher;
import com.rafaelfiume.tictactoe.support.ConsoleInputReaderStub;
import com.rafaelfiume.tictactoe.support.RecordConsoleOutputRenderer;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import static com.rafaelfiume.tictactoe.matchers.CustomBoardMatcher.aBoardLike;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;

@RunWith(SpecRunner.class)
public class ConsoleBoardGameHasWinnerTest extends TestState {

    private final ConsoleInputReaderStub consoleInputReader = new ConsoleInputReaderStub();

    private final Board board = new Board();

    private final ConsoleInput input = new ConsoleInput(consoleInputReader, board);

    private final RecordConsoleOutputRenderer renderer = new RecordConsoleOutputRenderer(board);

    private final ConsoleGameRunner gameRunner = new ConsoleGameRunner(board, input, renderer);

    @Test
    public void appDisplaysPlayerXHasVerticalWin() throws Exception {
        when(player_X_marksTopLeftPosition());
        and(player_O_marksCenterPosition());
        and(player_X_marksMidLeftPosition());
        and(player_O_marksMidRightPosition());
        and(player_X_marksDownLeftPosition());
        andAppIsUpAndRunning();

        then(theBoardGameDescription(), is("Player X:"));
        then(theBoardGame(), aBoardLike(
                " X |   |   \n" +
                "---+---+---\n" +
                " X | O | O \n" +
                "---+---+---\n" +
                " X |   |   \n"));
        then(theBoardGameStatus(), is("PLAYER X WON!"));
    }

    private void andAppIsUpAndRunning() {
        gameRunner.start();
    }

    private TestState and(ActionUnderTest actionUnderTest) throws Exception {
        return when(actionUnderTest);
    }

    private ActionUnderTest player_X_marksTopLeftPosition() {
        return (givens, capturedInputAndOutputs) -> {
            consoleInputReader.willReturn(1);
            return capturedInputAndOutputs;
        };
    }

    private ActionUnderTest player_O_marksCenterPosition() {
        return (givens, capturedInputAndOutputs) -> {
            consoleInputReader.willReturn(5);
            return capturedInputAndOutputs;
        };
    }

    private ActionUnderTest player_X_marksMidLeftPosition() {
        return (givens, capturedInputAndOutputs) -> {
            consoleInputReader.willReturn(4);
            return capturedInputAndOutputs;
        };
    }

    private ActionUnderTest player_O_marksMidRightPosition() {
        return (givens, capturedInputAndOutputs) -> {
            consoleInputReader.willReturn(6);
            return capturedInputAndOutputs;
        };
    }

    private ActionUnderTest player_X_marksDownLeftPosition() {
        return (givens, capturedInputAndOutputs) -> {
            consoleInputReader.willReturn(7);
            return capturedInputAndOutputs;
        };
    }

    private StateExtractor<Object> theBoardGameDescription() {
        return inputAndOutputs -> renderer.gameDescription();
    }

    private StateExtractor<String> theBoardGame() {
        return inputAndOutputs -> renderer.boarGame();
    }

    private StateExtractor<String> theBoardGameStatus() {
        return inputAndOutputs -> renderer.gameStatus();
    }

}
