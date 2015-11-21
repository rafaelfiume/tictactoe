package com.rafaelfiume.tictactoe;

import static java.lang.Character.isLetter;

public class Board {

    private final char[][] grid;

    private Player currentPlayer = Player.X;

    private boolean gameStarted = false;

    public Board() {
        this(new char[3][3]);
    }

    private Board(char[][] grid) {
        this.grid = grid;
    }

    public boolean isGameOver() {
        return hasVerticalWinner() || hasHorizontalWinner();
    }

    public void playerChooses(BoardPosition boardPosition) {
        grid[boardPosition.row()][boardPosition.column()] = currentPlayer.symbol();

        this.currentPlayer = switchPlayerIfGameIsNotOver();
        this.gameStarted = true;
    }

    public Player winner() {
        return (isGameOver()) ? currentPlayer : null;
    }

    public boolean isGameStarted() {
        return gameStarted;
    }

    public Player currentPlayer() {
        return currentPlayer;
    }

    public Board currentGameSnapshot() {
        final Board snapshot = new Board(cloneArray(grid));
        snapshot.currentPlayer = this.currentPlayer;
        snapshot.gameStarted = this.gameStarted;

        return snapshot;
    }

    char topLeft() {    return grid[0][0]; }
    char topCenter() {  return grid[0][1]; }
    char topRight() {   return grid[0][2]; }
    char midLeft() {    return grid[1][0]; }
    char center() {     return grid[1][1]; }
    char midRight() {   return grid[1][2]; }
    char downLeft() {   return grid[2][0]; }
    char downCenter() { return grid[2][1]; }
    char downRight() {  return grid[2][2]; }

    private Player switchPlayerIfGameIsNotOver() {
        if (isGameOver()) return currentPlayer;

        return (currentPlayer == Player.X)
                ? Player.O
                : Player.X;
    }

    private boolean hasVerticalWinner() {
        for (int i = 0; i < 3; i++) {
            if (!isLetter(grid[0][i])) {
                continue;
            }

            if ((grid[0][i] == grid[1][i]) && (grid[1][i] == grid[2][i])) {
                return true;
            }
        }

        return false;
    }

    private boolean hasHorizontalWinner() {
        for (int i = 0; i < 3; i++) {
            if (!isLetter(grid[i][0])) {
                continue;
            }

            if ((grid[i][0] == grid[i][1]) && (grid[i][1] == grid[i][2])) {
                return true;
            }
        }

        return false;
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
