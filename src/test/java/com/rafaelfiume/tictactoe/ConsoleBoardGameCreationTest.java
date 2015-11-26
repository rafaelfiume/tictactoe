package com.rafaelfiume.tictactoe;

import com.googlecode.yatspec.junit.SpecRunner;
import com.googlecode.yatspec.state.givenwhenthen.*;
import com.rafaelfiume.tictactoe.matchers.BoardMatcher;
import com.rafaelfiume.tictactoe.matchers.EmptyBoardMatcher;
import com.rafaelfiume.tictactoe.support.BoardBuilder;
import com.rafaelfiume.tictactoe.support.ConsoleInputReaderStub;
import com.rafaelfiume.tictactoe.support.RecordConsoleOutputRenderer;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import static com.rafaelfiume.tictactoe.matchers.EmptyBoardMatcher.anEmptyBoard;
import static java.lang.System.lineSeparator;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;

@RunWith(SpecRunner.class)
public class ConsoleBoardGameCreationTest extends TestState {

    private final ConsoleInputReaderStub consoleInputReader = new ConsoleInputReaderStub();

    private final Board board = new Board();

    private final RecordConsoleOutputRenderer consoleRenderer = new RecordConsoleOutputRenderer();

    private final ConsoleGameRunner gameRunner = new ConsoleGameRunner(board, consoleInputReader, consoleRenderer);

    @Test
    public void appDisplaysBoardToUsersWhenItStarts() throws Exception {
        given(appIsUpAndRunning());

        then(theGameDescription(), is("Game Board Creation..."));
        then(theGame(), shows(anEmptyBoard()));
        then(theGameStatus(), is("Board Created." + lineSeparator() + "The game will start with Player X"));
    }

    private GivensBuilder appIsUpAndRunning() {
        return givens -> {
            startApp();
            return givens;
        };
    }

    private void startApp() {
        try {
            gameRunner.start();
        } catch (NullPointerException e) {
            // This is a massive hack to deal with premature termination: game is not over an ConsoleInputReader#readUserInput will blow up
            // TODO RF 25/11/2015 Improve the design to fix it
        }
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

    private TypeSafeMatcher<String> shows(BoardMatcher boardMatcher) {
        return boardMatcher;
    }

}
