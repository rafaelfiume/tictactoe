package com.rafaelfiume.tictactoe;

import static com.rafaelfiume.tictactoe.BoardPosition.UNKNOWN;
import static java.lang.Character.isLetter;

public class Board {

    private final char[][] grid;

    private Player currentPlayer = Player.X; // this concerns turns
    private int currentTurn = 1; // this concerns turns

    private boolean gameStarted = false; // is this concerning the board???

    public Board() {
        this(new char[3][3]);
    }

    private Board(char[][] grid) {
        this.grid = grid;
    }

    public boolean gameHasNotStarted() {
        return !gameStarted;
    }

    public boolean gameIsRunning() {
        return !isGameOver();
    }

    public boolean isGameOver() {
        return gameIsOverWithAWinner() || gameIsOverWithADraw();
    }

    public boolean gameIsOverWithAWinner() {
        return hasVerticalWinner() || hasHorizontalWinner() || hasDiagonalWinner();
    }

    public boolean gameIsOverWithADraw() {
        return noMoreTurns() && !gameIsOverWithAWinner();
    }

    public Player winner() {
        return (gameIsOverWithAWinner()) ? currentPlayer : null;
    }

    public Player currentPlayer() {
        return currentPlayer;
    }

    public void playerChooses(BoardPosition boardPosition) {
        final boolean playerTurnEnded = markPositionIfAvailable(boardPosition); // this concerns turns

        if (playerTurnEnded) {
            this.currentPlayer = switchPlayerIfGameIsNotOver();
            this.currentTurn++;
        }

        this.gameStarted = true;
    }

    private boolean markPositionIfAvailable(BoardPosition position) {
        if (position == UNKNOWN
                || cellIsMarkedAt(grid[position.row()][position.column()])) {
            return false;
        }

        grid[position.row()][position.column()] = currentPlayer.symbol();
        return true;
    }

    public Board currentGameSnapshot() {
        final Board snapshot = new Board(cloneArray(grid));
        snapshot.currentPlayer = this.currentPlayer;
        snapshot.currentTurn = this.currentTurn;
        snapshot.gameStarted = this.gameStarted;

        return snapshot;
    }

    char topLeft() {      return grid[0][0]; }

    char topCenter() {    return grid[0][1]; }
    char topRight() {     return grid[0][2]; }
    char midLeft() {      return grid[1][0]; }
    char center() {       return grid[1][1]; }
    char midRight() {     return grid[1][2]; }
    char bottomLeft() {   return grid[2][0]; }
    char bottomCenter() { return grid[2][1]; }
    char bottomRight() {  return grid[2][2]; }

    int currentTurn() {
        return currentTurn;
    }

    private boolean noMoreTurns() {
        return currentTurn == 10;
    }

    private Player switchPlayerIfGameIsNotOver() {
        if (isGameOver()) return currentPlayer;
        return (currentPlayer == Player.X) ? Player.O : Player.X;
    }

    private boolean hasVerticalWinner() {
        for (int i = 0; i < 3; i++) {
            if (cellIsMarkedAt(grid[0][i])
                    && (grid[0][i] == grid[1][i])
                    && (grid[1][i] == grid[2][i])) {
                return true;
            }
        }
        return false;
    }

    private boolean hasHorizontalWinner() {
        for (int i = 0; i < 3; i++) {
            if (cellIsMarkedAt(grid[i][0])
                    && (grid[i][0] == grid[i][1])
                    && (grid[i][1] == grid[i][2])) {
                return true;
            }
        }
        return false;
    }

    private boolean hasDiagonalWinner() {
        if (cellIsMarkedAt(topLeft())
                && (topLeft() == center())
                && (center() == bottomRight())) {
            return true;
        }
        return cellIsMarkedAt(topRight())
                && (topRight() == center())
                && (center() == bottomLeft());
    }

    private boolean cellIsMarkedAt(Character c) {
        return isLetter(c);
    }

    private char[][] cloneArray(char[][] src) {
        int length = src.length;
        char[][] target = new char[length][src[0].length];
        for (int i = 0; i < length; i++) {
            System.arraycopy(src[i], 0, target[i], 0, src[i].length);
        }
        return target;
    }

}
