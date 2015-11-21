package com.rafaelfiume.tictactoe;

import com.rafaelfiume.tictactoe.matchers.EmptyBoardMatcher;
import org.hamcrest.Matcher;
import org.junit.Test;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertThat;

public class ConsoleGameModelTest {

    private final ConsoleGameModel model = new ConsoleGameModel();

    @Test
    public void printEmptyBoardWhenThereAreNoMovesYet() {
        assertThat(model.gameDescription(), containsString("Game Board Creation..."));
        assertThat(model.board(), containsEmptyBoard());
        assertThat(model.gameStatus(), containsString("Board Created.\nThe game will start with Player X"));
    }

    private Matcher<? super String> containsEmptyBoard() {
        return new EmptyBoardMatcher();
    }

}
