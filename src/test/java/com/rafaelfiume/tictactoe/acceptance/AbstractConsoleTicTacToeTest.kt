package com.rafaelfiume.tictactoe.acceptance

import com.googlecode.totallylazy.Sequences.sequence
import com.googlecode.yatspec.junit.SpecResultListener
import com.googlecode.yatspec.junit.WithCustomResultListeners
import com.googlecode.yatspec.plugin.sequencediagram.SequenceDiagramGenerator
import com.googlecode.yatspec.plugin.sequencediagram.SvgWrapper
import com.googlecode.yatspec.rendering.html.DontHighlightRenderer
import com.googlecode.yatspec.rendering.html.HtmlResultRenderer
import com.googlecode.yatspec.rendering.html.index.HtmlIndexRenderer
import com.googlecode.yatspec.state.givenwhenthen.ActionUnderTest
import com.googlecode.yatspec.state.givenwhenthen.GivensBuilder
import com.googlecode.yatspec.state.givenwhenthen.StateExtractor
import com.googlecode.yatspec.state.givenwhenthen.TestState
import com.rafaelfiume.tictactoe.TttGame.Factory.newGame
import com.rafaelfiume.tictactoe.console.ConsoleGameRunner
import com.rafaelfiume.tictactoe.support.ConsoleInputReaderStub
import com.rafaelfiume.tictactoe.support.RecordConsoleOutputRenderer

abstract class AbstractConsoleTicTacToeTest : TestState(), WithCustomResultListeners {

    private val consoleInputReader = ConsoleInputReaderStub()
    private val consoleRenderer = RecordConsoleOutputRenderer()
    private val gameRunner = ConsoleGameRunner(newGame(), consoleInputReader, consoleRenderer)

    @Throws(Exception::class)
    override fun getResultListeners(): Iterable<SpecResultListener> {
        return sequence(
                HtmlResultRenderer().withCustomHeaderContent(SequenceDiagramGenerator.getHeaderContentForModalWindows()).withCustomRenderer(SvgWrapper::class.java, DontHighlightRenderer()),
                HtmlIndexRenderer()).safeCast(SpecResultListener::class.java)
    }

    protected fun player_X_marksTopLeftPosition(): ActionUnderTest = userHitsNumber(1)
    protected fun player_X_marksTopCenterPosition(): ActionUnderTest = userHitsNumber(2)
    protected fun player_X_marksTopRightPosition(): ActionUnderTest = userHitsNumber(3)
    protected fun player_X_marksMidLeftPosition(): ActionUnderTest = userHitsNumber(4)
    protected fun player_X_marksCenterPosition(): ActionUnderTest = userHitsNumber(5)
    protected fun player_X_marksMidRightPosition(): ActionUnderTest = userHitsNumber(6)
    protected fun player_X_marksDownLeftPosition(): ActionUnderTest = userHitsNumber(7)
    protected fun player_X_marksDownCenterPosition(): ActionUnderTest = userHitsNumber(8)
    protected fun player_X_marksDownRightPosition(): ActionUnderTest = userHitsNumber(9)

    protected fun player_O_marksTopLeftPosition(): ActionUnderTest = userHitsNumber(1)
    protected fun player_O_marksTopCenterPosition(): ActionUnderTest = userHitsNumber(2)
    protected fun player_O_marksMidLeftPosition(): ActionUnderTest = userHitsNumber(4)
    protected fun player_O_marksCenterPosition(): ActionUnderTest = userHitsNumber(5)
    protected fun player_O_marksMidRightPosition(): ActionUnderTest = userHitsNumber(6)
    protected fun player_O_marksDownLeftPosition(): ActionUnderTest = userHitsNumber(7)
    protected fun player_O_marksDownRightPosition(): ActionUnderTest = userHitsNumber(9)

    protected fun player_O_entersInvalidCellInTheBoard(): ActionUnderTest = userHitsNumber(99)

    protected fun andAppIsUpAndRunning() {
        startAppWithAHack()
    }

    protected fun appIsUpAndRunning() = GivensBuilder { givens ->
        startAppWithAHack()
        givens
    }

    @Throws(Exception::class)
    // Bypassing Yatspec framework since its original given implementation is not helping much in these tests (tests are reading way better now)
    protected fun given(actionUnderTest: ActionUnderTest): TestState = `when`(actionUnderTest)

    @Throws(Exception::class)
    protected fun and(actionUnderTest: ActionUnderTest): TestState = `when`(actionUnderTest)

    protected fun theGameDescription() = StateExtractor { inputAndOutputs -> consoleRenderer.gameDescription() }
    protected fun theGame()            = StateExtractor { inputAndOutputs -> consoleRenderer.boarGame()        }
    protected fun theGameStatus()      = StateExtractor { inputAndOutputs -> consoleRenderer.gameStatus()      }

    private fun userHitsNumber(number: Int) = ActionUnderTest { givens, capturedInputAndOutputs ->
        consoleInputReader.willReturn(number)
        capturedInputAndOutputs
    }

    private fun startAppWithAHack() {
        try {
            gameRunner.start()
        } catch (e: NullPointerException) {
            // Test and app work all right, but...
            // TODO RF 25/11/2015 This is a massive hack to deal with premature termination: game is not over an ConsoleInputReader#readUserInput will blow up
        }
    }

}
