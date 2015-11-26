package com.rafaelfiume.tictactoe;

import com.googlecode.yatspec.junit.SpecRunner;
import com.googlecode.yatspec.state.givenwhenthen.ActionUnderTest;
import com.googlecode.yatspec.state.givenwhenthen.StateExtractor;
import com.googlecode.yatspec.state.givenwhenthen.TestState;
import com.rafaelfiume.tictactoe.support.ConsoleInputReaderStub;
import com.rafaelfiume.tictactoe.support.RecordConsoleOutputRenderer;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.rafaelfiume.tictactoe.matchers.BoardMatcher.showsABoardLike;
import static java.lang.System.lineSeparator;
import static org.hamcrest.Matchers.is;

@RunWith(SpecRunner.class)
public class ConsoleBoardGameSadPathTest extends TestState {

    private final ConsoleInputReaderStub consoleInputReader = new ConsoleInputReaderStub();

    private final Board board = new Board();
    //private final Turn turn = mock(turn);
    //private final TicTacToe ticTacToe = new TicTacToe(board, turn);

    private final RecordConsoleOutputRenderer consoleRenderer = new RecordConsoleOutputRenderer();

    private final ConsoleGameRunner gameRunner = new ConsoleGameRunner(board, consoleInputReader, consoleRenderer);

    @Test
    public void tellPlayerToChooseAnEmptyCellInTheBoardWhenShePicksUpOneThatHasBeenAlreadyChosen() throws Exception {
        when(player_X_marksTopLeftPosition());
        and(player_O_marksTopLeftPosition());
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
        when(player_X_marksTopLeftPosition());
        and(player_O_entersInvalidCellInTheBoard());
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

    private void andAppIsUpAndRunning() {
        startApp();
    }

    private void startApp() {
        try {
            gameRunner.start();
        } catch (NullPointerException e) {
            // This is a massive hack to deal with premature termination: game is not over an ConsoleInputReader#readUserInput will blow up
            // TODO RF 25/11/2015 Improve the design to fix it
        }
    }

    private TestState and(ActionUnderTest actionUnderTest) throws Exception {
        return when(actionUnderTest);
    }

    private ActionUnderTest player_X_marksTopLeftPosition() { return userHitsNumber(1); }
    private ActionUnderTest player_O_marksTopLeftPosition() { return userHitsNumber(1); }
    private ActionUnderTest player_O_marksCenterPosition() { return userHitsNumber(5); }
    private ActionUnderTest player_O_entersInvalidCellInTheBoard() {
        return userHitsNumber(99);
    }

    private ActionUnderTest userHitsNumber(int number) {
        return (givens, capturedInputAndOutputs) -> {
            consoleInputReader.willReturn(number);
            return capturedInputAndOutputs;
        };
    }

    private StateExtractor<Object> theGameDescription() {
        return inputAndOutputs -> consoleRenderer.gameDescription();
    }

    private StateExtractor<String> theGame() {
        return inputAndOutputs -> consoleRenderer.boarGame();
    }

    private StateExtractor<String> theGameStatus() {
        return inputAndOutputs -> consoleRenderer.gameStatus();
    }

}
