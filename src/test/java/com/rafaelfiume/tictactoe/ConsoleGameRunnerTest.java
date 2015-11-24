package com.rafaelfiume.tictactoe;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.MockitoAnnotations;

import static com.rafaelfiume.tictactoe.BoardPosition.TOP_LEFT;
import static org.mockito.Mockito.*;

public class ConsoleGameRunnerTest {

    private final Board board = mock(Board.class);
    private final ConsoleInputReader consoleInputReader = mock(ConsoleInputReader.class);
    private ConsoleGameRenderer consoleRenderer = mock(ConsoleGameRenderer.class);

    private final ConsoleGameRunner consoleRunner = new ConsoleGameRunner(board, consoleInputReader, consoleRenderer);

    @Captor ArgumentCaptor<Board> captor;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void passingTopLeftPositionToBoardWhenPlayerSelectsIt() {
        when(board.gameIsRunning()).thenReturn(true).thenReturn(false); // game is on
        when(consoleInputReader.readUserInput()).thenReturn(BoardPosition.TOP_LEFT); // user hits top left position

        consoleRunner.start();

        verify(board).playerChooses(TOP_LEFT);
    }

}
