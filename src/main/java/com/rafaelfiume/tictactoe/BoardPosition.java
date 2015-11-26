package com.rafaelfiume.tictactoe;

public enum BoardPosition {

    TOP_LEFT(1, 0, 0)   , TOP_CENTER(2, 0, 1)   , TOP_RIGHT(3, 0, 2),
    MID_LEFT(4, 1, 0)   , CENTER(5, 1, 1)       , MID_RIGHT(6, 1, 2),
    BOTTOM_LEFT(7, 2, 0), BOTTOM_CENTER(8, 2, 1), BOTTOM_RIGHT(9, 2, 2),

    UNKNOWN(-1, -1, -1);

    private final int input;
    private final int row;
    private final int column;

    BoardPosition(int input, int row, int column) {
        this.input = input;
        this.row = row;
        this.column = column;
    }

    public static BoardPosition of(int number) {
        for (BoardPosition p : BoardPosition.values()) {
            if (p.input == number) return p;
        }
        return UNKNOWN;
    }

    public int row() {
        return row;
    }

    public int column() {
        return column;
    }

}
