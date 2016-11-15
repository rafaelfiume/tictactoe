package com.rafaelfiume.tictactoe.support

import com.rafaelfiume.tictactoe.Game
import com.rafaelfiume.tictactoe.GameRenderer
import com.rafaelfiume.tictactoe.console.ConsoleGameRenderer

import java.lang.System.lineSeparator

class RecordConsoleOutputRenderer : GameRenderer {

    private val originalRenderer = ConsoleGameRenderer()
    private lateinit var lines: Array<String>

    override fun show(game: Game): String {
        val originalContent = originalRenderer.show(game)

        this.lines = originalContent.split(lineSeparator().toRegex())
                                    .dropLastWhile(String::isEmpty)
                                    .toTypedArray()
        return originalContent
    }

    fun gameDescription(): String = lines[1]

    fun boarGame(): String {
        if (lines.size < 6) {
            return ""
        }

        return  lines[3] + lineSeparator() +
                lines[4] + lineSeparator() +
                lines[5] + lineSeparator() +
                lines[6] + lineSeparator() +
                lines[7]
    }

    fun gameStatus(): String {
        if (lines.size < 10) {
            return ""
        }

        // ugly: status may have one or two lines
        val additionalStatusLine = if (lines.size == 11)
            lineSeparator() + lines[10]
        else
            ""

        return lines[9] + additionalStatusLine
    }
}
