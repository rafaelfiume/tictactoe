package com.rafaelfiume.tictactoe;

import com.rafaelfiume.tictactoe.matchers.EmptyBoardMatcher;
import com.rafaelfiume.tictactoe.support.BoardBuilder;
import org.hamcrest.Matcher;
import org.junit.Test;

import static com.rafaelfiume.tictactoe.matchers.BoardMatcher.showsABoardLike;
import static com.rafaelfiume.tictactoe.support.BoardBuilder.aBoardwithPlayerOWinningWithAnHorizontalLineOnTheBottom;
import static com.rafaelfiume.tictactoe.support.BoardBuilder.aBoardWithPlayerXWinningWithVerticalLineOnTheLeft;
import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertThat;

public class ConsoleGameModelTest {

    @Test
    public void printEmptyBoardWhenThereAreNoMovesYet() {
        final Board emptyBoard = new BoardBuilder().build().currentGameSnapshot();
        final ConsoleGameModel emptyGame = new ConsoleGameModel(emptyBoard);

        assertThat(emptyGame.gameDescription(), containsString("Game Board Creation..."));
        assertThat(emptyGame.board(), containsEmptyBoard());
        assertThat(emptyGame.gameStatus(), containsString("Board Created.\nThe game will start with Player X"));
    }

    @Test
    public void printPlayerXWinsWhenHeStrikesVerticalVictory() {
        final Board playerXWon = aBoardWithPlayerXWinningWithVerticalLineOnTheLeft().currentGameSnapshot();
        final ConsoleGameModel consoleModel = new ConsoleGameModel(playerXWon);

        assertThat(consoleModel.gameDescription(), containsString("Player X:"));
        assertThat(consoleModel.board(), showsABoardLike(
                        " X |   |   \n" +
                        "---+---+---\n" +
                        " X | O | O \n" +
                        "---+---+---\n" +
                        " X |   |   \n"));
        assertThat(consoleModel.gameStatus(), containsString("PLAYER X WON!"));
    }

    @Test
    public void printPlayerOWinsWhenSheStrikesHorizontalVictory() {
        final Board playerOWon = aBoardwithPlayerOWinningWithAnHorizontalLineOnTheBottom().currentGameSnapshot();
        final ConsoleGameModel consoleModel = new ConsoleGameModel(playerOWon);

        assertThat(consoleModel.gameDescription(), containsString("Player O:"));
        assertThat(consoleModel.board(), showsABoardLike(
                        "   |   | X \n" +
                        "---+---+---\n" +
                        "   | X | X \n" +
                        "---+---+---\n" +
                        " O | O | O \n"));
        assertThat(consoleModel.gameStatus(), containsString("PLAYER O WON!"));
    }

    private Matcher<? super String> containsEmptyBoard() {
        return new EmptyBoardMatcher();
    }

}
