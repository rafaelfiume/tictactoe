package com.rafaelfiume.tictactoe;

import com.googlecode.yatspec.junit.SpecRunner;
import com.googlecode.yatspec.state.givenwhenthen.GivensBuilder;
import com.googlecode.yatspec.state.givenwhenthen.StateExtractor;
import com.googlecode.yatspec.state.givenwhenthen.TestState;
import com.rafaelfiume.tictactoe.matchers.EmptyBoardMatcher;
import com.rafaelfiume.tictactoe.support.RecordConsoleOutputRenderer;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;

@RunWith(SpecRunner.class)
public class ConsoleBoardGameTest extends TestState {

    private final RecordConsoleOutputRenderer renderer = new RecordConsoleOutputRenderer();

    private final ConsoleGameRunner gameRunner = new ConsoleGameRunner(renderer);

    @Test
    public void appDisplaysBoardToUsersWhenItStarts() throws Exception {
        given(appIsUpAndRunning());

        then(theBoardGameDescription(), is("Game Board Creation..."));
        then(theBoardGame(), showsBoardToUser());
        then(theBoardGameStatus(), is("Board Created.\nThe game will start with Player X"));
    }

    private GivensBuilder appIsUpAndRunning() {
        return givens -> {
            gameRunner.start();
            return givens;
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

    private TypeSafeMatcher<String> showsBoardToUser() {
        return new EmptyBoardMatcher();
    }

}
