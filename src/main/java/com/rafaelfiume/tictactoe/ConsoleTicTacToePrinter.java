package com.rafaelfiume.tictactoe;

import static java.lang.String.format;
import static java.lang.System.lineSeparator;

public class ConsoleTicTacToePrinter {

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

    public ConsoleTicTacToePrinter(Board currentSnapshot) {
        this.snapshot = currentSnapshot;
        this.printerState = printerStateFrom(currentSnapshot);
    }

    private PrinterState printerStateFrom(Board currentSnapshot) {
        if (currentSnapshot.gameHasNotStarted())     return new EmptyBoardState();
        if (currentSnapshot.gameIsRunning())         return new PlayerTurnState(currentSnapshot.currentPlayer());
        if (currentSnapshot.gameIsOverWithAWinner()) return new PlayerWonState(currentSnapshot.winner());
        if (currentSnapshot.gameIsOverWithADraw())   return new DrawState();

        return null; // not there yet
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

    interface PrinterState {
        String gameDescription();
        String gameStatus();
    }

    class EmptyBoardState implements PrinterState {

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

    class PlayerTurnState implements PrinterState {

        private final Player player;

        public PlayerTurnState(Player player) {
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

    class PlayerWonState implements PrinterState {

        private final Player winner;

        public PlayerWonState(Player winner) {
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

    class DrawState implements PrinterState {

        @Override
        public String gameDescription() {
            return format("No More Turns Left :-)");
        }

        @Override
        public String gameStatus() {
            return format("GAME ENDS WITH A DRAW!");
        }
    }

    //
    // Boring methods
    //

    String topLeft() {      return markAt(snapshot.topLeft()); }
    String topCenter() {    return markAt(snapshot.topCenter()); }
    String topRight() {     return markAt(snapshot.topRight()); }
    String midLeft() {      return markAt(snapshot.midLeft()); }
    String center() {       return markAt(snapshot.center()); }
    String midRight() {     return markAt(snapshot.midRight()); }
    String bottomLeft() {   return markAt(snapshot.bottomLeft()); }
    String bottomCenter() { return markAt(snapshot.bottomCenter()); }
    String bottomRight() {  return markAt(snapshot.bottomRight());}

    private String markAt(char c) {
        return (isEmpty(c)) ? BLANK : Character.toString(c);
    }

    private boolean isEmpty(char c) {
        return !Character.isLetter(c);
    }

}
