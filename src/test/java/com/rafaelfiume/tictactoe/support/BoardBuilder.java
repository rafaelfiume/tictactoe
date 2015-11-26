package com.rafaelfiume.tictactoe.support;

import com.rafaelfiume.tictactoe.Board;
import com.rafaelfiume.tictactoe.BoardPosition;

import static com.rafaelfiume.tictactoe.BoardPosition.*;

public class BoardBuilder {

    private final Board board = new Board();

    public static Board emptyBoard() {
        return new BoardBuilder().build();
    }

    public static Board aBoardWithPlayerXWinningWithVerticalLineOnTheLeft() {
        return new BoardBuilder()
                .withPlayerXChoosing(BoardPosition.TOP_LEFT)
                .withPlayerOChoosing(BoardPosition.CENTER)
                .withPlayerXChoosing(BoardPosition.MID_LEFT)
                .withPlayerOChoosing(BoardPosition.MID_RIGHT)
                .withPlayerXChoosing(BoardPosition.BOTTOM_LEFT)
                .build();
    }

    public static Board aBoardWithPlayerOWinningWithAnHorizontalLineOnTheBottom() {
        return new BoardBuilder()
                .withPlayerXChoosing(TOP_RIGHT)
                .withPlayerOChoosing(BOTTOM_LEFT)
                .withPlayerXChoosing(CENTER)
                .withPlayerOChoosing(BOTTOM_CENTER)
                .withPlayerXChoosing(MID_RIGHT)
                .withPlayerOChoosing(BOTTOM_RIGHT)
                .build();
    }

    public static Board aBoardWithAGameEndingWithADraw() {
        return new BoardBuilder()
                .withPlayerXChoosing(TOP_LEFT)
                .withPlayerOChoosing(CENTER)
                .withPlayerXChoosing(MID_LEFT)
                .withPlayerOChoosing(BOTTOM_LEFT)
                .withPlayerXChoosing(TOP_RIGHT)
                .withPlayerOChoosing(BOTTOM_RIGHT)
                .withPlayerXChoosing(BOTTOM_CENTER)
                .withPlayerOChoosing(TOP_CENTER)
                .withPlayerXChoosing(MID_RIGHT)
                .build();
    }

    public BoardBuilder withPlayerXChoosing(BoardPosition boardPosition) {
        board.playerChooses(boardPosition);
        return this;
    }

    public BoardBuilder withPlayerOChoosing(BoardPosition boardPosition) {
        board.playerChooses(boardPosition);
        return this;
    }

    public BoardBuilder withPlayerXChoosingAnUnknownCell() {
        board.playerChooses(UNKNOWN);
        return this;
    }

    public Board build() {
        return board;
    }
}
