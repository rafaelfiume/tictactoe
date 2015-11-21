package com.rafaelfiume.tictactoe.support;

import com.rafaelfiume.tictactoe.Board;
import com.rafaelfiume.tictactoe.ConsoleGameRenderer;

import static java.lang.System.lineSeparator;

public class RecordConsoleOutputRenderer extends ConsoleGameRenderer {

    private String[] lines;

    public RecordConsoleOutputRenderer(Board board) {
        super(board);
    }

    @Override
    public String render() {
        final String originalContent = super.render();
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
                lines[5] + lineSeparator();
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
