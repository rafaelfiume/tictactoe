package com.rafaelfiume.tictactoe;

import com.googlecode.yatspec.junit.SpecRunner;
import com.googlecode.yatspec.state.givenwhenthen.ActionUnderTest;
import com.googlecode.yatspec.state.givenwhenthen.StateExtractor;
import com.googlecode.yatspec.state.givenwhenthen.TestState;
import com.rafaelfiume.tictactoe.support.ConsoleInputReaderStub;
import com.rafaelfiume.tictactoe.support.RecordConsoleOutputRenderer;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.rafaelfiume.tictactoe.matchers.CustomBoardMatcher.aBoardLike;
import static org.hamcrest.Matchers.is;

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

    @Test
    public void appDisplaysPlayerOHasHorizontalWin() throws Exception {
        when(player_X_marksTopLeftPosition());
        and(player_O_marksCenterPosition());
        and(player_X_marksTopCenterPosition());
        and(player_O_marksMidLeftPosition());
        and(player_X_marksDownLeftPosition());
        and(player_O_marksMidRightPosition());
        andAppIsUpAndRunning();

        then(theBoardGameDescription(), is("Player O:"));
        then(theBoardGame(), aBoardLike(
                        " X | X |   \n" +
                        "---+---+---\n" +
                        " O | O | O \n" +
                        "---+---+---\n" +
                        " X |   |   \n"));
        then(theBoardGameStatus(), is("PLAYER O WON!"));
    }

    @Test
    public void appDisplaysPlayerXHasDiagonalWin() throws Exception {
        when(player_X_marksTopLeftPosition());
        and(player_O_marksMidLeftPosition());
        and(player_X_marksCenterPosition());
        and(player_O_marksMidRightPosition());
        and(player_X_marksDownRightPosition());
        andAppIsUpAndRunning();

        then(theBoardGameDescription(), is("Player X:"));
        then(theBoardGame(), aBoardLike(
                        " X |   |   \n" +
                        "---+---+---\n" +
                        " O | X | O \n" +
                        "---+---+---\n" +
                        "   |   | X \n"));
        then(theBoardGameStatus(), is("PLAYER X WON!"));
    }

    private void andAppIsUpAndRunning() {
        gameRunner.start();
    }

    private TestState and(ActionUnderTest actionUnderTest) throws Exception {
        return when(actionUnderTest);
    }

    private ActionUnderTest player_X_marksTopLeftPosition() {
        return userHitsNumber(1);
    }

    private ActionUnderTest player_O_marksCenterPosition() {
        return userHitsNumber(5);
    }

    private ActionUnderTest player_X_marksMidLeftPosition() {
        return userHitsNumber(4);
    }

    private ActionUnderTest player_O_marksMidRightPosition() {
        return userHitsNumber(6);
    }

    private ActionUnderTest player_X_marksDownLeftPosition() {
        return userHitsNumber(7);
    }

    private ActionUnderTest player_O_marksMidLeftPosition() {
        return userHitsNumber(4);
    }

    private ActionUnderTest player_X_marksTopCenterPosition() {
        return userHitsNumber(2);
    }

    private ActionUnderTest player_X_marksCenterPosition() {
        return userHitsNumber(5);
    }

    private ActionUnderTest player_X_marksDownRightPosition() {
        return userHitsNumber(9);
    }

    private ActionUnderTest userHitsNumber(int number) {
        return (givens, capturedInputAndOutputs) -> {
            consoleInputReader.willReturn(number);
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
