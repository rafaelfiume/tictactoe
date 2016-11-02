package com.rafaelfiume.tictactoe.console

import com.rafaelfiume.tictactoe.BoardPosition
import com.rafaelfiume.tictactoe.BoardPosition.TOP_LEFT
import com.rafaelfiume.tictactoe.Game
import com.rafaelfiume.tictactoe.GameRenderer
import org.junit.Test
import org.mockito.BDDMockito.given
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

class ConsoleGameRunnerTest {

    private val game = mock(Game::class.java)
    private val consoleInputReader = mock(ConsoleInputReader::class.java)
    private val consoleRenderer = mock(GameRenderer::class.java)

    private val consoleRunner = ConsoleGameRunner(game, consoleInputReader, consoleRenderer)

    @Test
    fun passingTopLeftPositionToBoardWhenPlayerSelectsIt() {
        given(game.snapshot()).willReturn(game)

        given(game.gameIsOn()).willReturn(true).willReturn(false)
        given(consoleInputReader.readUserInput()).willReturn(BoardPosition.TOP_LEFT) // user hits top left position

        consoleRunner.start()

        verify(game).playerChooses(TOP_LEFT)
    }

}
