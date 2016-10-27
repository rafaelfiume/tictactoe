package com.rafaelfiume.tictactoe.console;

import com.rafaelfiume.tictactoe.Board;
import com.rafaelfiume.tictactoe.BoardPosition;
import com.rafaelfiume.tictactoe.GameRenderer;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import static com.rafaelfiume.tictactoe.BoardPosition.TOP_LEFT;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

public class ConsoleGameRunnerTest {

    private final Board board = mock(Board.class);
    private final ConsoleInputReader consoleInputReader = mock(ConsoleInputReader.class);
    private final GameRenderer consoleRenderer = mock(GameRenderer.class);

    private final ConsoleGameRunner consoleRunner = new ConsoleGameRunner(board, consoleInputReader, consoleRenderer);

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void passingTopLeftPositionToBoardWhenPlayerSelectsIt() {
        given(board.currentGameSnapshot()).willReturn(board);

        given(board.gameIsRunning()).willReturn(true).willReturn(false); // game is on
        given(consoleInputReader.readUserInput()).willReturn(BoardPosition.TOP_LEFT); // user hits top left position

        consoleRunner.start();

        verify(board).playerChooses(TOP_LEFT);
    }

}
