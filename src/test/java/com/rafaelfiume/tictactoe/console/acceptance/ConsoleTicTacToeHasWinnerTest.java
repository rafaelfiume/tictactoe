package com.rafaelfiume.tictactoe.console.acceptance;

import com.googlecode.yatspec.junit.SpecRunner;
import com.googlecode.yatspec.state.givenwhenthen.ActionUnderTest;
import com.googlecode.yatspec.state.givenwhenthen.StateExtractor;
import com.googlecode.yatspec.state.givenwhenthen.TestState;
import com.rafaelfiume.tictactoe.Board;
import com.rafaelfiume.tictactoe.console.ConsoleGameRunner;
import com.rafaelfiume.tictactoe.support.ConsoleInputReaderStub;
import com.rafaelfiume.tictactoe.support.RecordConsoleOutputRenderer;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.rafaelfiume.tictactoe.matchers.BoardMatcher.showsABoardLike;
import static java.lang.System.lineSeparator;
import static org.hamcrest.Matchers.is;

@RunWith(SpecRunner.class)
public class ConsoleTicTacToeHasWinnerTest extends TestState {

    private final ConsoleInputReaderStub consoleInputReader = new ConsoleInputReaderStub();

    private final Board board = new Board();

    private final RecordConsoleOutputRenderer consoleRenderer = new RecordConsoleOutputRenderer();

    private final ConsoleGameRunner gameRunner = new ConsoleGameRunner(board, consoleInputReader, consoleRenderer);

    @Test
    public void playerXWinsWhenGettingThreeInAVerticalRow() throws Exception {
        when(player_X_marksTopLeftPosition());
        and(player_O_marksCenterPosition());
        and(player_X_marksMidLeftPosition());
        and(player_O_marksMidRightPosition());
        and(player_X_marksDownLeftPosition());
        andAppIsUpAndRunning();

        then(theGameDescription(), is("Player X:"));
        then(theGame(), showsABoardLike(
                        " X |   |   " + lineSeparator() +
                        "---+---+---" + lineSeparator() +
                        " X | O | O " + lineSeparator() +
                        "---+---+---" + lineSeparator() +
                        " X |   |   "));
        then(theGameStatus(), is("PLAYER X WON!"));
    }

    @Test
    public void playerOWinsWhenGettingThreeInAHorizontalRow() throws Exception {
        when(player_X_marksTopLeftPosition());
        and(player_O_marksCenterPosition());
        and(player_X_marksTopCenterPosition());
        and(player_O_marksMidLeftPosition());
        and(player_X_marksDownLeftPosition());
        and(player_O_marksMidRightPosition());
        andAppIsUpAndRunning();

        then(theGameDescription(), is("Player O:"));
        then(theGame(), showsABoardLike(
                        " X | X |   " + lineSeparator() +
                        "---+---+---" + lineSeparator() +
                        " O | O | O " + lineSeparator() +
                        "---+---+---" + lineSeparator() +
                        " X |   |   "));
        then(theGameStatus(), is("PLAYER O WON!"));
    }

    @Test
    public void playerXWinsWhenGettingThreeInADiagonalRow() throws Exception {
        when(player_X_marksTopLeftPosition());
        and(player_O_marksMidLeftPosition());
        and(player_X_marksCenterPosition());
        and(player_O_marksMidRightPosition());
        and(player_X_marksDownRightPosition());
        andAppIsUpAndRunning();

        then(theGameDescription(), is("Player X:"));
        then(theGame(), showsABoardLike(
                        " X |   |   " + lineSeparator() +
                        "---+---+---" + lineSeparator() +
                        " O | X | O " + lineSeparator() +
                        "---+---+---" + lineSeparator() +
                        "   |   | X "));
        then(theGameStatus(), is("PLAYER X WON!"));
    }

    private void andAppIsUpAndRunning() {
        gameRunner.start();
    }

    private TestState and(ActionUnderTest actionUnderTest) throws Exception {
        return when(actionUnderTest);
    }

    private ActionUnderTest player_X_marksTopLeftPosition() { return userHitsNumber(1); }
    private ActionUnderTest player_X_marksTopCenterPosition() { return userHitsNumber(2); }
    private ActionUnderTest player_O_marksTopCenterPosition() { return userHitsNumber(2); }
    private ActionUnderTest player_X_marksTopRightPosition() { return userHitsNumber(3); }
    private ActionUnderTest player_X_marksMidLeftPosition() { return userHitsNumber(4);}
    private ActionUnderTest player_O_marksMidLeftPosition() { return userHitsNumber(4); }
    private ActionUnderTest player_X_marksCenterPosition() { return userHitsNumber(5); }
    private ActionUnderTest player_O_marksCenterPosition() { return userHitsNumber(5); }
    private ActionUnderTest player_X_marksMidRightPosition() { return userHitsNumber(6); }
    private ActionUnderTest player_O_marksMidRightPosition() { return userHitsNumber(6); }
    private ActionUnderTest player_X_marksDownLeftPosition() { return userHitsNumber(7); }
    private ActionUnderTest player_O_marksDownLeftPosition() { return userHitsNumber(7); }
    private ActionUnderTest player_X_marksDownCenterPosition() { return userHitsNumber(8); }
    private ActionUnderTest player_X_marksDownRightPosition() { return userHitsNumber(9); }
    private ActionUnderTest player_O_marksDownRightPosition() { return userHitsNumber(9); }

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
