package com.rafaelfiume.tictactoe;

import static java.lang.String.format;
import static java.lang.System.lineSeparator;

public class TicTacToePrinter {

    private static final String BLANK = " ";
    private static final String CHOOSE_POSITION = "Choose Position:";

    private final Board snapshot;

    private final PrinterState printerState;

    private final String boardTemplate = "" +
            " %s | %s | %s " + lineSeparator() +
            "---+---+---"    + lineSeparator() +
            " %s | %s | %s " + lineSeparator() +
            "---+---+---"    + lineSeparator() +
            " %s | %s | %s ";

    public TicTacToePrinter(Board currentSnapshot) {
        this.snapshot = currentSnapshot;
        this.printerState = printerStateFrom(currentSnapshot);
    }

    public String gameDescription() {
        return printerState.gameDescription();
    }

    public String board() {
        return format(boardTemplate,
                topLeft(), topCenter(), topRight(),
                midLeft(), center(), midRight(),
                bottomLeft(), bottomCenter(), bottomRight());
    }

    public String gameStatus() {
        return printerState.gameStatus();
    }

    private PrinterState printerStateFrom(Board currentSnapshot) {
        if (currentSnapshot.gameHasNotStarted())     return new EmptyBoardState();
        if (currentSnapshot.gameIsRunning())         return new PlayerTurnState(currentSnapshot.currentPlayer());
        if (currentSnapshot.gameIsOverWithAWinner()) return new PlayerWonState(currentSnapshot.winner());
        if (currentSnapshot.gameIsOverWithADraw())   return new DrawState();

        throw new IllegalStateException("Unknown board state");
    }

    private interface PrinterState {
        String gameDescription();
        String gameStatus();
    }

    private class EmptyBoardState implements PrinterState {

        @Override
        public String gameDescription() {
            return "Game Board Creation...";
        }

        @Override
        public String gameStatus() {
            return "Board Created." + lineSeparator() +
                    "The game will start with Player X" + lineSeparator() +
                    CHOOSE_POSITION;
        }
    }

    private class PlayerTurnState implements PrinterState {

        private final Player player;

        PlayerTurnState(Player player) {
            this.player = player;
        }

        @Override
        public String gameDescription() {
            return format("Player %s:", player);
        }

        @Override
        public String gameStatus() {
            return CHOOSE_POSITION;
        }
    }

    private class PlayerWonState implements PrinterState {

        private final Player winner;

        PlayerWonState(Player winner) {
            this.winner = winner;
        }

        @Override
        public String gameDescription() {
            return format("Player %s:", winner);
        }

        @Override
        public String gameStatus() {
            return format("PLAYER %s WON!", winner);
        }
    }

    private class DrawState implements PrinterState {

        @Override
        public String gameDescription() {
            return "No More Turns Left :-)";
        }

        @Override
        public String gameStatus() {
            return "GAME ENDS WITH A DRAW!";
        }
    }

    //
    // Boring methods
    //

    private String topLeft() {      return markAt(snapshot.topLeft()); }
    private String topCenter() {    return markAt(snapshot.topCenter()); }
    private String topRight() {     return markAt(snapshot.topRight()); }
    private String midLeft() {      return markAt(snapshot.midLeft()); }
    private String center() {       return markAt(snapshot.center()); }
    private String midRight() {     return markAt(snapshot.midRight()); }
    private String bottomLeft() {   return markAt(snapshot.bottomLeft()); }
    private String bottomCenter() { return markAt(snapshot.bottomCenter()); }
    private String bottomRight() {  return markAt(snapshot.bottomRight());}

    private String markAt(char c) {
        return (isEmpty(c)) ? BLANK : Character.toString(c);
    }

    private boolean isEmpty(char c) {
        return !Character.isLetter(c);
    }

}
