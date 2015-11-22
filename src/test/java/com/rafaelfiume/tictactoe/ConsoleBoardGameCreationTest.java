package com.rafaelfiume.tictactoe;

import com.googlecode.yatspec.junit.SpecRunner;
import com.googlecode.yatspec.state.givenwhenthen.*;
import com.rafaelfiume.tictactoe.matchers.EmptyBoardMatcher;
import com.rafaelfiume.tictactoe.support.BoardBuilder;
import com.rafaelfiume.tictactoe.support.ConsoleInputReaderStub;
import com.rafaelfiume.tictactoe.support.RecordConsoleOutputRenderer;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;

@RunWith(SpecRunner.class)
public class ConsoleBoardGameCreationTest extends TestState {

    private final ConsoleInputReaderStub consoleInputReader = new ConsoleInputReaderStub();

    private final Board board = mock(Board.class);
    private final ConsoleInput input = new ConsoleInput(consoleInputReader, board);

    private final RecordConsoleOutputRenderer renderer = new RecordConsoleOutputRenderer(board);

    private final ConsoleGameRunner gameRunner = new ConsoleGameRunner(board, input, renderer);

    @Test
    public void appDisplaysBoardToUsersWhenItStarts() throws Exception {
        given(appIsUpAndRunning());

        then(theGameDescription(), is("Game Board Creation..."));
        then(theGame(), showsAnEmptyBoardToUser());
        then(theGameStatus(), is("Board Created.\nThe game will start with Player X"));
    }

    private GivensBuilder appIsUpAndRunning() {
        return givens -> {
            Mockito.when(board.isGameOver()).thenReturn(true); // prematurely terminates the game
            Mockito.when(board.currentGameSnapshot()).thenReturn(new BoardBuilder().build());

            gameRunner.start();
            return givens;
        };
    }

    private StateExtractor<Object> theGameDescription() {
        return inputAndOutputs -> renderer.gameDescription();
    }

    private StateExtractor<String> theGame() {
        return inputAndOutputs -> renderer.boarGame();
    }

    private StateExtractor<String> theGameStatus() {
        return inputAndOutputs -> renderer.gameStatus();
    }

    private TypeSafeMatcher<String> showsAnEmptyBoardToUser() {
        return new EmptyBoardMatcher();
    }

}
