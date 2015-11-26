package com.rafaelfiume.tictactoe.support;

import com.rafaelfiume.tictactoe.Board;
import com.rafaelfiume.tictactoe.BoardPosition;

import static com.rafaelfiume.tictactoe.BoardPosition.*;

public class BoardBuilder {

    private final Board board = new Board();

    public static Board aBoardWithPlayerXWinningWithVerticalLineOnTheLeft() {
        return new BoardBuilder()
                .withPlayerXChoosing(BoardPosition.TOP_LEFT)
                .withPlayerOChoosing(BoardPosition.CENTER)
                .withPlayerXChoosing(BoardPosition.MID_LEFT)
                .withPlayerOChoosing(BoardPosition.MID_RIGHT)
                .withPlayerXChoosing(BoardPosition.DOWN_LEFT)
                .build();
    }

    public static Board aBoardWithPlayerOWinningWithAnHorizontalLineOnTheBottom() {
        return new BoardBuilder()
                .withPlayerXChoosing(TOP_RIGHT)
                .withPlayerOChoosing(DOWN_LEFT)
                .withPlayerXChoosing(CENTER)
                .withPlayerOChoosing(DOWN_CENTER)
                .withPlayerXChoosing(MID_RIGHT)
                .withPlayerOChoosing(DOWN_RIGHT)
                .build();
    }

    public static Board aBoardWithAGameEndingWithADraw() {
        return new BoardBuilder()
                .withPlayerXChoosing(TOP_LEFT)
                .withPlayerOChoosing(CENTER)
                .withPlayerXChoosing(MID_LEFT)
                .withPlayerOChoosing(DOWN_LEFT)
                .withPlayerXChoosing(TOP_RIGHT)
                .withPlayerOChoosing(DOWN_RIGHT)
                .withPlayerXChoosing(DOWN_CENTER)
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

    public Board build() {
        return board;
    }
}
