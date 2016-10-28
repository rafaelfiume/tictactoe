package com.rafaelfiume.tictactoe.console;

import com.rafaelfiume.tictactoe.BoardPosition;
import com.rafaelfiume.tictactoe.Game;
import com.rafaelfiume.tictactoe.GameRenderer;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import static com.rafaelfiume.tictactoe.BoardPosition.TOP_LEFT;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

public class ConsoleGameRunnerTest {

    private final Game game = mock(Game.class);
    private final ConsoleInputReader consoleInputReader = mock(ConsoleInputReader.class);
    private final GameRenderer consoleRenderer = mock(GameRenderer.class);

    private final ConsoleGameRunner consoleRunner = new ConsoleGameRunner(game, consoleInputReader, consoleRenderer);

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void passingTopLeftPositionToBoardWhenPlayerSelectsIt() {
        given(game.snapshot()).willReturn(game);

        given(game.gameIsOn()).willReturn(true).willReturn(false);
        given(consoleInputReader.readUserInput()).willReturn(BoardPosition.TOP_LEFT); // user hits top left position

        consoleRunner.start();

        verify(game).playerChooses(TOP_LEFT);
    }

}
