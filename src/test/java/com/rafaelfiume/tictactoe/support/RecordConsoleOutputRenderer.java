package com.rafaelfiume.tictactoe.support;

import com.rafaelfiume.tictactoe.Game;
import com.rafaelfiume.tictactoe.GameRenderer;
import com.rafaelfiume.tictactoe.console.ConsoleGameRenderer;

import static java.lang.System.lineSeparator;

public class RecordConsoleOutputRenderer implements GameRenderer {

    private final GameRenderer originalRenderer = new ConsoleGameRenderer();
    private String[] lines;

    @Override
    public String show(Game game) {
        final String originalContent = originalRenderer.show(game);
        this.lines = originalContent.split(lineSeparator());
        return originalContent;
    }

    public String gameDescription() {
        if (lines == null) {
            return "";
        }

        return lines[1];
    }

    public String boarGame() {
        if (lines.length < 6) {
            return "";
        }

        return lines[3] + lineSeparator() +
                lines[4] + lineSeparator() +
                lines[5] + lineSeparator() +
                lines[6] + lineSeparator() +
                lines[7];
    }

    public String gameStatus() {
        if (lines.length <10) {
            return "";
        }

        final String additionalStatusLine = (lines.length == 12)
                ? lineSeparator() + lines[10]
                : "";

        return lines[9] + additionalStatusLine;
    }
}
