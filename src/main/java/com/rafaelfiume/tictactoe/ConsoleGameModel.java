package com.rafaelfiume.tictactoe;

import static java.lang.String.format;
import static java.lang.System.lineSeparator;

public class ConsoleGameModel {

    private static final String BLANK = " ";
    private static final String CHOOSE_POSITION = "Choose Position:";

    private final Board snapshot;

    private final GameState gameState;

    private final String boardTemplate = "" +
            " %s | %s | %s " + lineSeparator() +
            "---+---+---"    + lineSeparator() +
            " %s | %s | %s " + lineSeparator() +
            "---+---+---"    + lineSeparator() +
            " %s | %s | %s ";

    public ConsoleGameModel(Board currentSnapshot) {
        this.snapshot = currentSnapshot;
        this.gameState = gameStateFrom(currentSnapshot);
    }

    private GameState gameStateFrom(Board currentSnapshot) {
        if (!currentSnapshot.isGameStarted()) return new EmptyBoardState();

        if (!currentSnapshot.isGameOver()) return new PlayerTurnState(currentSnapshot.currentPlayer());

        if (currentSnapshot.isGameOver()) return new PlayerWonState(currentSnapshot.winner());
        return null; // not there yet
    }

    public String gameDescription() {
        return gameState.gameDescription();
    }

    public String board() {
        return format(boardTemplate,
                topLeft(), topCenter(), topRight(),
                midLeft(), center(), midRight(),
                downLeft(), downCenter(), downRight());
    }

    public String gameStatus() {
        return gameState.gameStatus();
    }

    interface GameState {
        String gameDescription();
        String gameStatus();
    }

    class EmptyBoardState implements GameState {

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

    class PlayerTurnState implements GameState {

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

    class PlayerWonState implements GameState {

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

    //
    // Boring methods
    //

    String topLeft() {    return (isEmpty(snapshot.topLeft()))    ? BLANK : Character.toString(snapshot.topLeft()); }
    String topCenter() {  return (isEmpty(snapshot.topCenter()))  ? BLANK : Character.toString(snapshot.topCenter()); }
    String topRight() {   return (isEmpty(snapshot.topRight()))   ? BLANK : Character.toString(snapshot.topRight()); }
    String midLeft() {    return (isEmpty(snapshot.midLeft()))    ? BLANK : Character.toString(snapshot.midLeft()); }
    String center() {     return (isEmpty(snapshot.center()))     ? BLANK : Character.toString(snapshot.center()); }
    String midRight() {   return (isEmpty(snapshot.midRight()))   ? BLANK : Character.toString(snapshot.midRight()); }
    String downLeft() {   return (isEmpty(snapshot.downLeft()))   ? BLANK : Character.toString(snapshot.downLeft()); }
    String downCenter() { return (isEmpty(snapshot.downCenter())) ? BLANK : Character.toString(snapshot.downCenter()); }
    String downRight() {  return (isEmpty(snapshot.downRight()))  ? BLANK : Character.toString(snapshot.downRight());}

    private boolean isEmpty(char c) {
        return !Character.isLetter(c);
    }

}
