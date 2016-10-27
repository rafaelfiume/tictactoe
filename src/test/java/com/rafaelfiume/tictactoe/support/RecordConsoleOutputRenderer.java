package com.rafaelfiume.tictactoe.support;

import com.rafaelfiume.tictactoe.Board;
import com.rafaelfiume.tictactoe.GameRenderer;
import com.rafaelfiume.tictactoe.console.ConsoleGameRenderer;

import static java.lang.System.lineSeparator;

public class RecordConsoleOutputRenderer implements GameRenderer {

    private final GameRenderer originalRenderer = new ConsoleGameRenderer();
    private String[] lines;

    @Override
    public String render(Board gameSnapshot) {
        final String originalContent = originalRenderer.render(gameSnapshot);
        this.lines = originalContent.split(lineSeparator());
        return originalContent;
    }

    public String gameDescription() {
        if (lines == null) {
            return "";
        }

        return lines[0];
    }

    public String boarGame() {
        if (lines.length < 6) {
            return "";
        }

        return lines[1] + lineSeparator() +
                lines[2] + lineSeparator() +
                lines[3] + lineSeparator() +
                lines[4] + lineSeparator() +
                lines[5];
    }

    public String gameStatus() {
        if (lines.length < 8) {
            return "";
        }

        final String additionalStatusLine = (lines.length == 10)
                ? lineSeparator() + lines[8]
                : "";

        return lines[7] + additionalStatusLine;
    }
}
