package com.rafaelfiume.tictactoe;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.MockitoAnnotations;

import static com.rafaelfiume.tictactoe.BoardPosition.TOP_LEFT;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ConsoleInputTest {

    private final ConsoleInputReader consoleInputReader = mock(ConsoleInputReader.class);
    private final Board board = mock(Board.class);
    private final ConsoleInput input = new ConsoleInput(consoleInputReader, board) ;

    @Captor ArgumentCaptor<Board> captor;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void passingTopLeftPositionToBoardWhenPlayerSelectsIt() {
        when(consoleInputReader.readUserInput()).thenReturn(1);

        input.read();

        verify(board).playerChooses(TOP_LEFT);
    }

}
